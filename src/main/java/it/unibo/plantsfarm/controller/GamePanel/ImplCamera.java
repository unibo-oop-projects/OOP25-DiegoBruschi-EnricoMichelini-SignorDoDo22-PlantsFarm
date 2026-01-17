package it.unibo.plantsfarm.controller.GamePanel;

import it.unibo.plantsfarm.controller.GamePanel.api.Camera;
import it.unibo.plantsfarm.model.Player.BasePlayer;

public class ImplCamera implements Camera{
    public int x = 0;
    public int y = 0;
    private int posX;
    private int posY;
    private BasePlayer player;
    private final int screenWidth;
    private final int screenHeight;
    private final int worldWidth;
    private final int worldHeight;

    public ImplCamera(int screenWidth, int screenHeight, int worldWidth, int worldHeight){
        
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    @Override
    public void followPlayer() {
        
        x = (int)player.getPosx() - screenWidth / 2;
        y = (int)player.getPosy() - screenHeight / 2;
        
        if (x < 0) x = 0;
        if (y < 0) y = 0;
            
    }

    @Override
    public int getCameraPosX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCameraPosX'");
    }

    @Override
    public int getCameraPosY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCameraPosY'");
    }

    public void setPlayer(BasePlayer player){
        this.player = player;
    }
    
}
