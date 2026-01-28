package it.unibo.plantsfarm.model.camera;

import it.unibo.plantsfarm.model.player.api.Player;

/**
 * 
 */
public interface Camera {
    /**
     * Calculate the position of camera based on player position. 
     */
    void followPlayer();
    /**
     * Return Position of the camera on the X axis.
     * 
     * @return PositionX of camera
     */
    int getCameraPosX();

    /**
     * Return Position of the camera on the Y axis.
     * 
     * @return PositionY of camera 
     */
    int getCameraPosY();

    /**
     * Set the entity to follow.
     * 
     * @param player set the current player from the ControllerGamePanel
     */
    void setPlayer(Player player);
}
