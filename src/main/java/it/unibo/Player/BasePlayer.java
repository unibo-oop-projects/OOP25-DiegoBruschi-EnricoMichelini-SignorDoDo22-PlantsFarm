package it.unibo.Player;

import static it.unibo.GamePanel.api.ControllerGamePanel.UserInput.*;

    

import it.unibo.GamePanel.api.ControllerGamePanel.UserInput;

public class BasePlayer {
    
    private double posX = 0;
    private double posY = 0;
    private double SPEED = 200;
    private UserInput direction = FERMO;
    public BasePlayer(){}

   public void updatePlayer( long time) {

    double delta = SPEED * time / 1000.0;
        switch (direction) {
            case LEFT -> posX -= delta;
            case RIGHT -> posX += delta;
            case UP -> posY -= delta;
            case DOWN -> posY += delta;
            case FERMO -> {}
            
        }
    }

    public void setDirection(UserInput direction) {
        this.direction = direction;
    }
    public double getPosx(){ return this.posX;}
    public double getPosy(){ return this.posY;}

}
