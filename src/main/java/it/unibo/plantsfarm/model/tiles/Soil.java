package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unibo.plantsfarm.model.plant.Plant;

/**
 * Represents a soil tile in the game, which can be planted with a plant.
 */
public final class Soil implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Rectangle coordinatePod;
    private final int tileId;
    private boolean isPlanted;
    private Plant plant;

    /**
     * Creates a new Soil tile with the specified coordinates and tile ID.
     * 
     * @param coordinatePod The coordinates of the soil tile as a Rectangle.
     * @param tileId The ID of the tile, used to identify the type of soil.
     */
    public Soil(final Rectangle coordinatePod, final int tileId) {
        this.coordinatePod = new Rectangle(coordinatePod);
        this.tileId = tileId;
    }

    /**
     * Gets the coordinates of the soil tile.
     * 
     * @return The coordinates of the soil tile as a Rectangle.
     */
    public Rectangle getCoordinate() {
        return new Rectangle(this.coordinatePod);
    }

    /**
     * Plants a plant in the soil tile.
     * 
     * @param selectedPlant The plant to be planted in the soil tile.
     */
    public void setPlanted(final Plant selectedPlant) {
        this.isPlanted = true;
        this.plant = new Plant(selectedPlant.getType());
    }

    /**
     * Checks if the soil tile is planted with a plant.
     * 
     * @return true if the soil tile is planted, false otherwise.
     */
    public boolean isPlanted() {
        return this.isPlanted;
    }

    /**
     * Gets the plant planted in the soil tile.
     * 
     * @return The plant planted in the soil tile, or null if none.
     */
    public Plant getPlant() {
        return this.plant;
    }

    /**
     * Gets the tile ID of the soil tile.
     * 
     * @return The tile ID of the soil tile.
     */
    public int getTileId() {
        return this.tileId;
    }

    /**
     * Removes the plant from the soil tile, making it unplanted.
     */
    public void removePlant() {
        this.plant = null;
        this.isPlanted = false;
    }
}
