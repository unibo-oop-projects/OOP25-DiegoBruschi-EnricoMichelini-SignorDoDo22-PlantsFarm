package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.plantsfarm.model.tiles.Soil;

/**
 * Represents a specific area in the garden containing multiple soils.
 * TO DO: ornamentals boost effect of nearby plants.
 */
public final class PlantArea {

    private final Rectangle bounds;
    private final List<Soil> soils = new ArrayList<>();
    //private Soil centerSoil;

    /**
     * Creates a new PlantArea.
     * 
     * @param bounds The rectangle defining the area limits.
     */
    public PlantArea(final Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Adds a soil to this area if it is contained.
     * Find out if the soil is the center of the area.
     * 
     * @param soil The soil object to try to add.
     */
    public void addSoil(final Soil soil) {
        if (bounds.contains(soil.getCoordinate())) {
            soils.add(soil);
            if (soil.getCoordinate().contains(bounds.getCenterX(), bounds.getCenterY())) {
                //this.centerSoil = soil;
            }
        }
    }
}
