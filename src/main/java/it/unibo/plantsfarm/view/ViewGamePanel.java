package it.unibo.plantsfarm.view;

import static it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.plantsfarm.controller.GamePanel.ImplCamera;
import it.unibo.plantsfarm.controller.GamePanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.view.Map.TileManager;


public class ViewGamePanel extends JPanel {

  
  public int orginalTileSize = 16;
  public final int scale = 3; 
  public int tileSize = orginalTileSize * scale;
  public int maxScreenCol = 34;
  public int maxScreenRow = 18;
  private final int windthScreen = tileSize * maxScreenCol;
  private final int heighScreen = tileSize * maxScreenRow;
  private BufferedImage currentImagePlayer;
  private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER =
    Map.of(KeyEvent.VK_W, UP, KeyEvent.VK_A, LEFT, KeyEvent.VK_D, RIGHT, KeyEvent.VK_S, DOWN, KeyEvent.VK_R, AZIONE);
  public TileManager tileM;

  public ImplCamera camera;
  int xcam = 0;
  int ycam = 0;
  private double playerPosX = 100;
  private double playerPosY = 100;
  private ImplControllerGamePanel controller;
  public Boolean azioneDisenga = false;
  
  
    public ViewGamePanel(){
      
      this.camera = new ImplCamera(700, 700, 34, 18);
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
          }
        }

        @Override
        public void keyReleased(final KeyEvent e) {
        if (KEY_MAPPER.containsKey(e.getKeyCode())) {
            controller.takeInput(UserInput.FERMO);
        }
      }
        
    });
  }

  
  public void show(final double playerPosX, final double playerPosY, final BufferedImage currentImagePlayer ){
    SwingUtilities.invokeLater(() -> {
      
      this.playerPosX = playerPosX;
      this.playerPosY = playerPosY;
      this.currentImagePlayer = currentImagePlayer;
      repaint();
      
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.fillRect(1576, 800, WIDTH, HEIGHT);
    tileM.drawTile(g2D);
    g2D.drawImage(currentImagePlayer,(int)playerPosX - camera.x,(int)playerPosY - camera.y,64,64, null);
  }

  public void setController(final ImplControllerGamePanel controller){
    this.controller = controller;
  }

}  

