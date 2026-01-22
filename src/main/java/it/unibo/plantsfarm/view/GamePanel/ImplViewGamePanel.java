package it.unibo.plantsfarm.view.GamePanel;

import static it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import it.unibo.plantsfarm.controller.GamePanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.view.Animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.Map.TileManager;


public class ImplViewGamePanel extends JPanel {

  public static int orginalTileSize = 16;
  public final static int SCALE = 3; 
  public static int tileSize = orginalTileSize * SCALE;
  public final static int maxScreenCol = 66; //66
  public final static int maxScreenRow = 21; //21
  public final static int windthScreen = tileSize * maxScreenCol;
  public final static int heighScreen = tileSize * maxScreenRow;
  private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER =
    Map.of(KeyEvent.VK_W, UP, KeyEvent.VK_A, LEFT, KeyEvent.VK_D, RIGHT, KeyEvent.VK_S, DOWN, KeyEvent.VK_R, AZIONE);
  private TileManager tileM;

  private int cameraX; 
  private int cameraY; 
  private double playerPosX;
  private double playerPosY;
  private ImplControllerGamePanel controller;
  private SelectorFrames selector;
  
  public ImplViewGamePanel(){
    
    this.requestFocus();
    this.setVisible(true);
    this.setDoubleBuffered(true);
    this.setSize(700,700);
    this.setFocusable(true);
    this.requestFocusInWindow(true);
    this.setBackground(Color.black);
    this.tileM = new TileManager(this);
    this.addKeyListener(new KeyAdapter() {
    
    @Override
    public void keyPressed(final KeyEvent e) {
      super.keyPressed(e);
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

  public void show(final double playerPosX, final double playerPosY, int cameraX, int cameraY ){
    SwingUtilities.invokeLater(() -> {
      this.playerPosX = playerPosX;
      this.playerPosY = playerPosY;
      this.cameraX = cameraX;
      this.cameraY = cameraY;
      repaint();
    });
  }

  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    final Graphics2D g2D = (Graphics2D) g;
    tileM.drawTile(g2D, cameraX, cameraY);
    g2D.drawImage(selector.getCurrentImage(), (int) playerPosX - cameraX, (int)playerPosY - cameraY, 64,64,null);
  }

  public void setController(final ImplControllerGamePanel controller){
    this.controller = controller;
  }

  public void setSelectorFrames(final SelectorFrames selector){
    this.selector = selector;
  }

  public int getWidthScreen(){ 
    return this.windthScreen; 
  }

  public int getHeightScreen(){ 
    return this.heighScreen; 
  }

}  

