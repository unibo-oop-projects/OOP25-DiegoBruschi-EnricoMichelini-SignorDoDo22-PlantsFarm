package it.unibo.model.Player;

import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput;

import java.awt.image.*;

import it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput;

public class BasePlayer  {
    private double posX;
    private double posY;
    private double speed = 200;
    private UserInput direction = UserInput.FERMO;
    enum StatePlayer { IN_MOVEMENT, IN_ACTION};
    StatePlayer state = null;
   
    public BasePlayer() { }
   /**
    * Updates the player's position starting from the last known position.
    * The displacement is computed using the elapsed time to ensure
    * smooth and frame-rate independent movement.
    *
    * @param time the time passed since the previous update, in milliseconds
    */

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

    /**
     * Updates the current movement direction of the player according to
     * the latest user input.
     *
     * @param direction the direction requested by the user
     */
    public final  void setDirection(final UserInput direction) {
        this.direction = direction;
    }

    /**
     * Returns the current x-coordinate of the player.
     * 
     * @return the current x position
     */
    public final  double getPosx() { 
        return this.posX; 
    }

    /**
     * Returns the current y-coordinate of the player.
     * 
     * @return the current y position
     */
    public final double getPosy() { 
        return this.posY; 
    }

    public final UserInput getDirection(){ 
        return this.direction;
    }




   
}
