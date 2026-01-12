package it.unibo.plantsfarm.view.Map;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3; // Optimal is 3

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 36; //66 for full map, 32 for small map, 36 for full screen
    public final int maxScreenRow = 19; //21 for full map, 19 for small map
    final int screenWidth = this.getWidth(); // 768 pixels
    final int screenHeight = this.getHeight(); // 576 pixels

    TileManager tileM = new TileManager(this);
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {

            // Repaint the screen
            repaint();

            try {
                Thread.sleep(16); // Making the thread sleep for 16ms makes the screen refresh 60 times per second (60 FPS)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

    }
}
