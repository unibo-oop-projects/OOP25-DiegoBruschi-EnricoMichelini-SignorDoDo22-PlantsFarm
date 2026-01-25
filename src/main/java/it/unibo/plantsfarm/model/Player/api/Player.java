package it.unibo.plantsfarm.model.Player.api;

import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.Mappa;

/**
 * Abstract base class representing a generic player entity.
 * It stores the player position, movement speed and direction.
 */
public abstract class Player {

    /** Current X position of the player in world coordinates. */
    private double posX = 100;

    /** Current Y position of the player in world coordinates. */
    private double posY = 100;

    /** Movement speed of the player (units per second). */
    private double speed = 200;

    /** Current movement direction of the player. */
    private UserInput direction = UserInput.FERMO;

    /**
     * Updates the position of the player based on the elapsed time
     * and the current movement direction.
     *
     * @param time the elapsed time since the last update, in milliseconds
     * @param mappaModel the game map model (used for future collision checks)
     */
    public void updatePlayer(final long time, final Mappa mappaModel) {
        final double delta = speed * time / 1000.0;

        double nextPosX = posX;
        double nextPosY = posY;

        switch (direction) {
            case LEFT -> nextPosX -= delta;
            case RIGHT -> nextPosX += delta;
            case UP -> nextPosY -= delta;
            case DOWN -> nextPosY += delta;
            case FERMO -> { }
        }

        if (nextPosX >= 0 && nextPosY >= 0) {
            posX = nextPosX;
            posY = nextPosY;
        }
    }

    /**
     * Sets the current movement direction of the player.
     *
     * @param direction the new movement direction
     */
    public final void setDirection(final UserInput direction) {
        this.direction = direction;
    }

    /**
     * Returns the current X position of the player.
     *
     * @return the X coordinate in world space
     */
    public final double getPosx() {
        return this.posX;
    }

    /**
     * Returns the current Y position of the player.
     *
     * @return the Y coordinate in world space
     */
    public final double getPosy() {
        return this.posY;
    }

    /**
     * Returns the current movement direction of the player.
     *
     * @return the last direction set for the player
     */
    public final UserInput getDirection() {
        return this.direction;
    }
}