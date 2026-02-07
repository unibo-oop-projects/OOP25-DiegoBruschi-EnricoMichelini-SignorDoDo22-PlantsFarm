package it.unibo.plantsfarm.model.player.api;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;

import java.awt.Rectangle;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.garden.CollisionDetector;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

/**
 * Abstract base class representing a generic player entity.
 * It stores the player position, movement speed and direction.
 */
public abstract class AbstractPlayer {

    public static final int FARMER_SPEED = 400;
    public static final int EXPERT_FARMER_SPEED = 650;

    /** Movement speed of the player (units per second). */
    @SuppressWarnings("checkstyle:VisibilityModifier")
    protected double speed;

    /** Current X position of the player in world coordinates. */
    private double posX = ImplViewGamePanel.WORLD_WIDTH / 2;

    /** Current Y position of the player in world coordinates. */
    private double posY = ImplViewGamePanel.WORLD_HEIGHT / 2;

    private double nextPosX;

    private double nextPosY;

    private final Rectangle solidArea = new Rectangle(8, 32, 32, 16);

    /** Current movement direction of the player. */
    private UserInput direction = UserInput.FERMO;

    private final ModelInventario inventory;
    private final CollisionDetector collisionDetector = new CollisionDetector(this);

    public AbstractPlayer(final ModelInventario inventory) {
        this.inventory = inventory;
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
            case ACTIONHOE -> { }
            case ACTIONWATER -> inventory.getItem(WATERCAN).useItem();
            case FERMO -> { }
        }

        if (!collisionDetector.collisionDetection()) {
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

    public final double getNextPosX() {
        return this.nextPosX;
    }

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

    /**
     * Return the Inventory of the player.
     *
     * @return
     */
    public ModelInventario getInventory() {
        return this.inventory;
    }

    /**
     * Return the HitBox of the player.
     *
     * @return
     */
    public final Rectangle getHitBox() {
        return new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);
    }
}
