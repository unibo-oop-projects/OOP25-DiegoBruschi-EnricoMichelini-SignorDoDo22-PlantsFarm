package it.unibo.plantsfarm.model.player.api;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;

import java.awt.Rectangle;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
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
    protected double speed;

    private TileMap map = new TileMap();

    /** Current X position of the player in world coordinates. */
    private double posX = ImplViewGamePanel.WORLD_WIDTH / 2;

    /** Current Y position of the player in world coordinates. */
    private double posY = ImplViewGamePanel.WORLD_HEIGHT / 2;

    private final Rectangle solidArea = new Rectangle(8, 32, 32, 16);

    /** Current movement direction of the player. */
    private UserInput direction = UserInput.FERMO;

    private final ModelInventario inventory;

    public AbstractPlayer(final ModelInventario inventory) {
        this.inventory = inventory;
        this.map.loadMap("/maps/map.txt");
    }


    /**
     * Updates the position of the player based on the elapsed time
     * and the current movement direction.
     *
     * @param time the elapsed time since the last update, in milliseconds
     */
    public void updatePlayer(final long time) {
        final double delta = speed * time / 1000.0;
        double nextPosX = posX;
        double nextPosY = posY;
        //System.out.println("Current Player State: " + direction);
        switch (direction) {
            case LEFT -> nextPosX -= delta;
            case RIGHT -> nextPosX += delta;
            case UP -> nextPosY -= delta;
            case DOWN -> nextPosY += delta;
            case ACTIONHOE -> { }
            case ACTIONWATER -> inventory.getItem(WATERCAN).useItem();
            case FERMO -> { }
        }

        final int futureSolidX = (int) (nextPosX + solidArea.x);
        final int futureSolidY = (int) (nextPosY + solidArea.y);
        final Rectangle futureHitbox = new Rectangle(futureSolidX, futureSolidY, solidArea.width, solidArea.height);

        boolean collisionDetected = false;

        for (final SolidBlock tile : map.solidBlocks) {
            if (tile.getCoordinate().intersects(futureHitbox)) {
                collisionDetected = true;
                break;
            }
        }

        if (!collisionDetected) {
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

    public ModelInventario getInventory() {
        return this.inventory;
    }

    public final Rectangle getHitBox() {
        return solidArea;
    }
}
