package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unibo.plantsfarm.model.plant.Plant;

public final class Soil implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Rectangle coordinatePod;
    private final int tileId;
    private boolean isPlanted;
    private Plant plant;

    public Soil(final Rectangle coordinatePod, final int tileId) {
        this.coordinatePod = coordinatePod;
        this.tileId = tileId;
    }

    public Rectangle getCoordinate() {
        return this.coordinatePod;
    }

    public void setPlanted(final Plant selectedPlant) {
        this.isPlanted = true;
        this.plant = selectedPlant;
    }

    public boolean isPlanted() {
        return this.isPlanted;
    }

    public Plant getPlant() {
        return this.plant;
    }

    public int getTileId() {
        return this.tileId;
    }

    public void removePlant() {
        this.plant = null;
        this.isPlanted = false;
    }
}
