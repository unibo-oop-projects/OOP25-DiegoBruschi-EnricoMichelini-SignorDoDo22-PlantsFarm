package it.unibo.plantsfarm.view.gamePanel;

import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.plantsfarm.controller.action.SeedController;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.view.Inventario;
import it.unibo.plantsfarm.view.map.TileManager;
import it.unibo.plantsfarm.view.utility.SpriteLoader;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.gamePanel.api.ViewGamePael;
import it.unibo.plantsfarm.model.Pod;

public class ImplViewGamePanel extends JPanel implements ViewGamePael{
  public static int orginalTileSize = 16;
  List<Pod> listPod = new LinkedList<>(List.of());
  public final static int SCALE = 3; 
  public static int tileSize = orginalTileSize * SCALE;
  public final static int MAXSCREENCOL = 66; 
  public final static int MAXSCREENROW = 21; 
  public final static int worldWidth = tileSize * (MAXSCREENCOL - 2); 
  public final static int worldheigh = tileSize * (MAXSCREENROW - 2); 
  public final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
  public final static int screenHeigh = Toolkit.getDefaultToolkit().getScreenSize().height;
  private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER =
    Map.of(KeyEvent.VK_W, UP, KeyEvent.VK_A, LEFT, KeyEvent.VK_D, RIGHT, KeyEvent.VK_S, DOWN, KeyEvent.VK_R, AZIONE);
  private TileManager tileM;

  private int cameraX; 
  private int cameraY; 
  private double playerPosX;
  private double playerPosY;
  private Image image = new SpriteLoader("/plantsStage/TomatoStage/Tomato5.png").getImage();
  private ImplControllerGamePanel controller;
  private SelectorFrames selector;

  private boolean plantWindow = true; //da modificare in base alle piante da visualizzare

  Boolean inventario = false;
  Inventario inventory = new Inventario(this);
  
  public ImplViewGamePanel(){
    this.requestFocus();
    this.setVisible(true);
    this.setDoubleBuffered(true);
    this.setSize(screenWidth,screenHeigh);
    this.setFocusable(true);
    this.requestFocusInWindow(true);
    this.setBackground(Color.black);
    this.tileM = new TileManager(this);
    this.addKeyListener(new KeyAdapter() {
    
    @Override
    public void keyPressed(final KeyEvent e) {
      super.keyPressed(e);
      if(e.getKeyCode() == KeyEvent.VK_I ){
          inventario = !inventario;
          repaint();  
        }

                if (e.getKeyCode() == KeyEvent.VK_P) {
                    new SeedController(selectedPlant -> {
                        System.out.println("Selected plant: " + selectedPlant.getName());
                    }, plantWindow).start();
                }
                
                if (KEY_MAPPER.containsKey(e.getKeyCode())) {
                    controller.takeInput(KEY_MAPPER.get(e.getKeyCode()));
                    selector.takeInput(KEY_MAPPER.get(e.getKeyCode()));
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                if (KEY_MAPPER.containsKey(e.getKeyCode())) {
                    controller.takeInput(UserInput.FERMO);
                }
    }});
  }

  public void show(final double playerPosX, final double playerPosY, int cameraX, int cameraY, List<Pod> listPod) {
    SwingUtilities.invokeLater(() -> {
      this.playerPosX = playerPosX;
      this.playerPosY = playerPosY;
      this.cameraX = cameraX;
      this.cameraY = cameraY;
      this.listPod = listPod;
      //System.out.println("LISTA POD: " + listPod.size());
      repaint();
    });
  }

  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    final Graphics2D g2D = (Graphics2D) g;
    tileM.drawTile(g2D, cameraX, cameraY);
    g2D.drawImage(selector.getCurrentImage(), (int) playerPosX - cameraX, (int)playerPosY  - cameraY, 64,64,null);
    if(inventario){
      inventory.createInventory(g2D);
    }
    
    for (Pod pod : listPod) {
      if(pod.getisPlanted() != false){
        g2D.drawImage(image, pod.getCoordinate().x - cameraX, pod.getCoordinate().y - cameraY, 48, 48, null);
        // System.out.println(pod.getCoordinate().x + ", " + pod.getCoordinate().y);
      }
    }
  }

  @Override
  public void setController(final ImplControllerGamePanel controller){
    this.controller = controller;
  }

  @Override
  public void setSelectorFrames(final SelectorFrames selector){
    this.selector = selector;
  }
}