package it.unibo.plantsfarm.model.Camera;

import it.unibo.plantsfarm.model.Player.FarmerPlayer;
import it.unibo.plantsfarm.view.GamePanel.ImplViewGamePanel;

public final class ImplCamera implements Camera {
    private int posX;
    private int posY;
    private FarmerPlayer player;
    private final int screenWidth;
    private final int screenHeight;
    private final int worldWidth = ImplViewGamePanel.worldWidth;
    private final int worldHeight = ImplViewGamePanel.worldheigh;
   
    public ImplCamera (final int screenWidth, final int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    @Override
    public void followPlayer() {

        posX = (int) player.getPosx() - ImplViewGamePanel.screenWidth / 2; //per la telecamera 
        posY = (int) player.getPosy() - ImplViewGamePanel.screenHeigh / 2; 

        if (posX < 0){ 
            posX = 0;
        } else if (posY < 0) {
            posY = 0;
        } else if (posX > worldWidth - screenWidth) { 
            posX = worldWidth - ImplViewGamePanel.screenWidth;
        } else if (posY > worldHeight - screenHeight) { 
            posY = worldHeight - ImplViewGamePanel.screenHeigh;
        }
    }

    @Override
    public int getCameraPosX() {
        return this.posX;
    }

    @Override
    public int getCameraPosY() {
        return this.posY;
    }

    @Override
    public void setPlayer(final FarmerPlayer player) {
        this.player = player;
    }
}