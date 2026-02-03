package it.unibo.plantsfarm.model.player.api;

import java.util.LinkedList;
import java.util.List;
import java.awt.Rectangle;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.TileMap;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.Soil;
import it.unibo.plantsfarm.model.SolidBlock;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;


/**
 * Abstract base class representing a generic player entity.
 * It stores the player position, movement speed and direction.
 */
public abstract class AbstractPlayer {

    public static final int FARMER_SPEED = 300;
    public static final int EXPERT_FARMER_SPEED = 500;

    /** Movement speed of the player (units per second). */
    protected double speed;

    private List<Soil> soils = new LinkedList<>(List.of());

    private final Rectangle solidArea = new Rectangle(8, 32, 32, 16);

    private TileMap map = new TileMap();

    /** Current X position of the player in world coordinates. */
    private double posX = ImplViewGamePanel.WORLD_WIDTH / 2;

    /** Current Y position of the player in world coordinates. */
    private double posY = ImplViewGamePanel.WORLD_HEIGHT / 2;

    /** Current movement direction of the player. */
    private UserInput direction = UserInput.FERMO;

    private final List<ItemsFarm> inventario;

    public AbstractPlayer(final List<ItemsFarm> inventario) {
        this.map.loadMap("/maps/map.txt");
        this.soils = this.map.getSoilList();
        this.inventario = inventario;
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
            case ACTIONHOE -> pianta(ImplViewGamePanel.selectedPlant);
            case ACTIONWATER -> innaffia(System.currentTimeMillis());
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

    public final void pianta(PlantType plant) {
        if (plant != null){
            Plant pianta = new Plant(plant);
            final Rectangle hitbox = new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);
            for (final Soil zolla : map.soilList) {
                if (zolla.getCoordinate().contains(hitbox)) {
                    if (!zolla.getIsPlanted()) {
                        zolla.setPlanted(pianta);
                        System.out.println(zolla.getPlant());
                        System.out.println("PLANT TYPE" + zolla.getPlant().currentStageTime);
                    }
                }
            }
        }
    }

    public final void innaffia(final long now) {
        final Rectangle hitbox = new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);
        for (final Soil zolla : map.soilList) {
            if (zolla.getCoordinate().contains(hitbox)) {
                if (zolla.getIsPlanted()) {
                    zolla.getPlant().water(now);
                    break;
                }
            }
        }
    }

    public final void updateSoil(final Long now) {
        for (final Soil zolla : map.soilList) {
            final Plant plant = zolla.getPlant();
            if (plant != null) {
                plant.updateNeedsWater(now);
                plant.increaseGrowthStage(now);
                //System.out.println(plant.toString());
            }
        }
    }

   public final List<Soil> getSoils() {
        return this.soils;
    }

}
