package it.unibo.plantsfarm.model.Player;

import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;

public final class BasePlayer  {
    private double posX = 0;
    private double posY = 0;
    private double speed = 200;
    private UserInput direction = UserInput.FERMO;

   public void updatePlayer(final long time) {
        final double delta = speed * time / 1000.0;
        switch (direction) {
            case LEFT -> posX -= delta;
            case RIGHT -> posX += delta;
            case UP -> posY -= delta;
            case DOWN -> posY += delta;
            case FERMO -> { }
        }
    }

    public final  void setDirection(final UserInput direction) {
        this.direction = direction;
    }

    public final  double getPosx() { 
        return this.posX; 
    }

    public final double getPosy() { 
        return this.posY; 
    }

    public final UserInput getDirection(){ 
        return this.direction;
    }
}
