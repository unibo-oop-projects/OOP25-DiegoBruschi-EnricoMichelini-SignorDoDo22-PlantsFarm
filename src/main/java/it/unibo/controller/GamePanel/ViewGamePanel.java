package it.unibo.controller.GamePanel;

import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.controller.GamePanel.api.ControllerGamePanel;
import it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput;

public class ViewGamePanel extends JPanel {

public boolean azioneDisegna = false;
public long azioneStartTime = 0;

  public int orginalTileSize = 16;
  public final int scale = 3; 
  public int tileSize = orginalTileSize * scale;
  public int maxScreenCol = 33;
  public int maxScreenRow = 19;
  private final int windthScreen = tileSize * maxScreenCol;
  private final int heighScreen = tileSize * maxScreenRow;
  private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER =
    Map.of(KeyEvent.VK_W, UP, KeyEvent.VK_A, LEFT, KeyEvent.VK_D, RIGHT, KeyEvent.VK_S, DOWN, KeyEvent.VK_R, AZIONE);
  


  private double playerPosX = 100;
  private double playerPosY = 100;
  private ImplControllerGamePanel controller;
  public Boolean azioneDisenga = false;
  
  
    public ViewGamePanel(){
      this.setBackground(Color.WHITE);
      this.setDoubleBuffered(true);
      this.setVisible(true);
      this.setSize(700,700);
      this.setFocusable(true);
      this.setBackground(Color.BLACK);
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

  
  public void show(final double playerPosX, final double playerPosY) {
    SwingUtilities.invokeLater(() -> {
      this.playerPosX = playerPosX;
      this.playerPosY = playerPosY;
      repaint();
      
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    System.out.println(Thread.currentThread().getName());
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.fillRect(100, 100, 64,64);
    g2D.drawImage(controller.controllerAnimation.getCurrentImage(),(int) playerPosX,(int)playerPosY,64,64, null);
  }

  public void setController(final ImplControllerGamePanel controller){
    this.controller = controller;
  }

 
}  

