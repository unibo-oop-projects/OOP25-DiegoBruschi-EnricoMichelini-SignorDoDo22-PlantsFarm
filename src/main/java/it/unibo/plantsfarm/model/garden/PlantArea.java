package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.plantsfarm.model.tiles.Soil;

//Represents a specific area in the garden containing multiple soils.
//TO DO: ornamentals boost effect of nearby plants.
public class PlantArea {
    
    private final Rectangle bounds;
    private final List<Soil> soils = new ArrayList<>();
    //private Soil centerSoil; 

    public PlantArea(Rectangle bounds) {
        this.bounds = bounds;
    }    public void addSoil(Soil soil) {
        if (bounds.contains(soil.getCoordinate())) {
            soils.add(soil);
            if (soil.getCoordinate().contains(bounds.getCenterX(), bounds.getCenterY())) {
                //this.centerSoil = soil;
            }
        }
    }
}
