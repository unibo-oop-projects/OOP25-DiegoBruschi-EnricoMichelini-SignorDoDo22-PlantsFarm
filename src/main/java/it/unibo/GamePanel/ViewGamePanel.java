package it.unibo.GamePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.GamePanel.api.ImplControllerGamePanel;


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
    private Image image;
    private int playerPosX;
    private int playerPosY;
    final int FPS = 60;
    private ImplControllerGamePanel controller;



    public ViewGamePanel()
    {
        image = new ImageIcon("icons\\frame_001.png").getImage();
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(TileSize * MaxScreenCol, TileSize * MaxScreenRow));
        playerPosX = 100;
        playerPosY = 100;
    }

  
    public void paintComponent(Graphics g){
      {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        g2D.drawImage(image, playerPosX, playerPosY, 64, 64, null);
      }
    }

    public void setController(ImplControllerGamePanel controller){
        this.controller = controller;
    }
    
    
}
    

