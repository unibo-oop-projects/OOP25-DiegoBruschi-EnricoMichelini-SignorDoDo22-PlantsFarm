package it.unibo.GamePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.GamePanel.api.ControllerGamePanel;
import it.unibo.GamePanel.api.ControllerGamePanel.UserInput;

import static  it.unibo.GamePanel.api.ControllerGamePanel.UserInput.*;

public class ViewGamePanel extends JPanel 
{
    /* 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = screenSize.width;
    private final int height = screenSize.height;
    */

    private final int OrginalTileSize = 16;
    private final int scale = 3; 
    private final int TileSize = OrginalTileSize * scale;
    private final int MaxScreenCol = 16;
    private final int MaxScreenRow = 12;
    private final int WindthScreen = TileSize * MaxScreenCol;
    private final int screenHeigh = TileSize * MaxScreenRow;
    private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER =
      Map.of(KeyEvent.VK_W, UP, KeyEvent.VK_A, LEFT, KeyEvent.VK_D, RIGHT, KeyEvent.VK_S, DOWN);
    
 
    private double playerPosX;
    private double playerPosY;
    private ImplControllerGamePanel controller;

    public ViewGamePanel(){
      this.setBackground(Color.WHITE);
      this.setDoubleBuffered(true);
      this.setVisible(true);
      this.setFocusable(true);
      this.setPreferredSize(new Dimension(TileSize * MaxScreenCol, TileSize * MaxScreenRow));
      this.setFocusable(true);
      
      this.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          super.keyPressed(e);
          if (KEY_MAPPER.containsKey(e.getKeyCode())){
                    controller.takeInput(KEY_MAPPER.get(e.getKeyCode()));
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        if (KEY_MAPPER.containsKey(e.getKeyCode())) {
            controller.takeInput(UserInput.FERMO);
        }
      }
    });
  }



    public void show(double playerPosX, double playerPosY){
      SwingUtilities.invokeLater(()->{
        this.playerPosX = playerPosX;
        this.playerPosY = playerPosY;
        repaint();
      });
    }

    public void paintComponent(Graphics g){
      
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      g2D.setColor(Color.RED);
      g2D.fillRect((int)playerPosX, (int)playerPosY, 64, 64);
      
    }

    public void setController(ImplControllerGamePanel controller){
        this.controller = controller;
    }
    
    
}
    

