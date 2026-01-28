package it.unibo.plantsfarm.model;

import java.awt.Rectangle;

public class Pod {
    
    private Rectangle coordinatePod;
    private boolean isPlanted;
    
    public Pod(Rectangle coordinatePod){
        this.coordinatePod = coordinatePod;
    }

    public Rectangle getCoordinate(){
        return this.coordinatePod;
    }

    public boolean getisPlanted(){
        return this.isPlanted;
    }
}
