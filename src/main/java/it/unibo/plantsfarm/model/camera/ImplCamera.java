package it.unibo.plantsfarm.model.camera;

import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public final class ImplCamera implements Camera {
    private int posX;
    private int posY;
    private Player player;
    private final int screenWidth;
    private final int screenHeight;
    private final int worldWidth = ImplViewGamePanel.WORLD_WIDTH;
    private final int worldHeight = ImplViewGamePanel.WORLD_HEIGHT;
   
    public ImplCamera (final int screenWidth, final int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    @Override
    public void followPlayer() {
        
        posX = (int) player.getPosx() - ImplViewGamePanel.SCREEN_WIDTH / 2; 
        posY = (int) player.getPosy() - ImplViewGamePanel.SCREEN_HEIGHT / 2; 
        if (posX < 0) { 
            posX = 0;
        } 
        if (posY < 0) {
            posY = 0;
        } 
        if (posX > worldWidth - screenWidth) { 
            posX = worldWidth - ImplViewGamePanel.SCREEN_WIDTH;
        } 
        if (posY > worldHeight - screenHeight) { 
            posY = worldHeight - ImplViewGamePanel.SCREEN_HEIGHT;
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
    public void setPlayer(final Player player) {
        this.player = player;
    }
}