package it.unibo.plantsfarm.model.camera;

import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Represents the game camera.
 * The camera defines the portion of the game world currently visible on screen
 * and follows the player within the world boundaries.
 *
 */
public final class ImplCamera {

    private int posX;
    private int posY;
    private final int screenWidth;

    private final int screenHeight;

    /**
     * Creates a new camera with the given screen dimensions.
     *
     * @param screenWidth  the width of the visible screen
     * @param screenHeight the height of the visible screen
     */
    public ImplCamera(final int screenWidth, final int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    /**
     * Updates the camera position by centering it on the player.
     * The camera position is clamped so that it does not move outside
     * the world boundaries, ensuring the player remains visible.
     *
     */
    public void followPlayer(final int playerPosX, final int playerPosY) {

        posX = (int) playerPosX - ImplViewGamePanel.SCREEN_WIDTH / 2;
        posY = (int) playerPosY - ImplViewGamePanel.SCREEN_HEIGHT / 2;

        if (posX < 0) {
            posX = 0;
        }
        if (posY < 0) {
            posY = 0;
        }
        if (posX > ImplViewGamePanel.WORLD_WIDTH - screenWidth) {
            posX = ImplViewGamePanel.WORLD_WIDTH - ImplViewGamePanel.SCREEN_WIDTH;
        }
        if (posY > ImplViewGamePanel.WORLD_HEIGHT - screenHeight) {
            posY = ImplViewGamePanel.WORLD_HEIGHT - ImplViewGamePanel.SCREEN_HEIGHT;
        }
    }

    /**
     * Returns the X coordinate of the camera.
     *
     * @return the camera X position in world space
     */
    public int getCameraPosX() {
        return this.posX;
    }

    /**
     * Returns the Y coordinate of the camera.
     *
     * @return the camera Y position in world space
     */
    public int getCameraPosY() {
        return this.posY;
    }

}
