package it.unibo.plantsfarm.model;

import java.awt.Rectangle;

public class Soil {
    
    private Rectangle coordinatePod;
    private boolean isPlanted;
    
    public Soil(Rectangle coordinatePod){
        this.coordinatePod = coordinatePod;
    }

    public Rectangle getCoordinate(){
        return this.coordinatePod;
    }

    public boolean getisPlanted(){
        return this.isPlanted;
    }
}
