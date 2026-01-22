package it.unibo.plantsfarm.model.Camera;

import it.unibo.plantsfarm.model.Player.BasePlayer;

public final class ImplCamera implements Camera {
    private int posX;
    private int posY;
    private BasePlayer player;
    private final int screenWidth;
    private final int screenHeight;

    public ImplCamera(final int screenWidth, final int screenHeight, final int worldWidth, final int worldHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    @Override
    public void followPlayer() {
        posX = (int) player.getPosx() - screenWidth / 2;
        posY = (int) player.getPosy() - screenHeight / 2;    
        if (posX < 0) {
            posX = 0;
        }
        if (posY < 0) {
            posY = 0;
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
    public void setPlayer(final BasePlayer player) {
        this.player = player;
    }
}
