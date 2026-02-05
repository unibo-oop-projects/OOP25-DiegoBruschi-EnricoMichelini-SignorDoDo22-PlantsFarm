package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import it.unibo.plantsfarm.model.plant.Plant;

public final class Soil {

    private Rectangle coordinatePod;
    private boolean isPlanted;
    private Plant plant;

    public Soil(final Rectangle coordinatePod) {
        this.coordinatePod = coordinatePod;
    }

    public Rectangle getCoordinate() {
        return this.coordinatePod;
    }

    public void setPlanted(final Plant selectedPlant) {
        this.isPlanted = true;
        this.plant = selectedPlant;
    }

    public boolean getIsPlanted() {
        return this.isPlanted;
    }

    public Plant getPlant() {
        return this.plant;
    }
}
