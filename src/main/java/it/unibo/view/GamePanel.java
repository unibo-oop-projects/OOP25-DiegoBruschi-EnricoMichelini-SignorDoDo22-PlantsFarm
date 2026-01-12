package it.unibo.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import it.unibo.view.Tile;
import it.unibo.view.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 2; // Optimal is 3

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 32; //66 for full map, 32 for small map
    public final int maxScreenRow = 19; //21 for full map, 19 for small map
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Spawn the player at the center of the spawn area
    int playerX = 144;
    int playerY = 480;

    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            // Move player position (sostituire da Fra)
            update();

            // Repaint the screen
            repaint();

            try {
                Thread.sleep(16); // Making the thread sleep for 16ms makes the screen refresh 60 times per second (60 FPS)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Player position update method (sostituire da Fra)
    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

         // Draw player (sostituire da Fra)
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
