package it.unibo.plantsfarm.model.player.api;

import java.awt.Rectangle;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.player.ExpertFarmer;
import it.unibo.plantsfarm.model.player.FarmerPlayer;
import it.unibo.plantsfarm.model.player.PlayersTypes;

/**
 * Abstract base class representing a generic player entity.
 * It stores the player position, movement speed and direction.
 */
public class AbstractPlayer {

    public static final int FARMER_SPEED = 400;
    public static final int OFF_SET_HITBOX = 32;
    public static final int WIDTH_HITBOX = 32;
    public static final int HEIGH_HITBOX = 32;
    public static final int EXPERT_FARMER_SPEED = 650;
    public static final int SPAWN_X = 192;
    public static final int SPAWN_Y = 720;

    private double speed;
    private double posX = SPAWN_X;
    private double posY = SPAWN_Y;
    private double nextPosX;
    private double nextPosY;
    private UserInput direction = UserInput.FERMO;
    private final ModelInventario inventory;
    private Plant heldPlant;
    private final PlayersTypes playersType;

    /**
     * Create the player and inventory.
     * to try all features select {@link ExpertFarmer}.
     * to try normal gameplay select {@link FarmerPlayer}.
     *
     * @param inventory that will be set based on player type.
     * @param playersTypes set the player type
     * @throws NullPointerException if {@code inventory} is {@code null}
     */
    public AbstractPlayer(final ModelInventario inventory, final PlayersTypes playersTypes) {
        this.inventory = inventory;
        this.playersType = playersTypes;
    }

    /**
     * Updates the position of the player based on the elapsed time
     * and the current movement direction.
     *
     * @param time the elapsed time since the last update, in milliseconds
     */
    public void updatePlayer(final long time) {
        final double delta = speed * time / 1000.0;
        nextPosX = posX;
        nextPosY = posY;
        switch (direction) {
            case LEFT -> nextPosX -= delta;
            case RIGHT -> nextPosX += delta;
            case UP -> nextPosY -= delta;
            case DOWN -> nextPosY += delta;
            case ACTIONHOE, ACTIONWATER, REMOVE_PLANT -> { }
            case FERMO -> { }
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
     * Return the next possible position for the player
     * on the X world.
     *
     * @return the next position player on X world.
     */
    public final double getNextPosX() {
        return this.nextPosX;
    }

    /**
     * Return the next possible position for the player on the Y world.
     *
     * @return the next position player on Y world.
     */
    public final double getNextPosY() {
        return this.nextPosY;
    }

    /**
     * Returns the current movement direction of the player.
     *
     * @return the last direction set for the player
     */
    public final UserInput getDirection() {
        return this.direction;
    }

    public final ModelInventario getInventory(){
        return this.inventory;
    }

    /**
     * Returns the player's hitbox used for collision detection.
     *
     * @return the current hitbox in world coordinates
     */
    public final Rectangle getHitBox() {
        return new Rectangle((int) posX + OFF_SET_HITBOX, (int) posY + OFF_SET_HITBOX, WIDTH_HITBOX, HEIGH_HITBOX);
    }

    /**
     * Function for move the player to the next calculated position
     * if the postion is valid.
     */
    public void applyMovement() {
        this.posX = nextPosX;
        this.posY = nextPosY;
    }

    /**
     * Set the player's speed based on player type.
     *
     * @param speed speed to be set.
     */
    protected final void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * Return the type of player created.
     *
     * @return the type player
     */
    public PlayersTypes getPlayerType() {
        return this.playersType;
    }

}
