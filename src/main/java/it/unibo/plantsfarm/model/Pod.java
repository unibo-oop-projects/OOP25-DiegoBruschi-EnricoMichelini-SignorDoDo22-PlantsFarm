package it.unibo.plantsfarm.model;

import java.awt.Rectangle;

import it.unibo.plantsfarm.model.plant.Plant;

public class Pod {
    
    private Rectangle coordinatePod;
    private boolean isPlanted;
    private Plant plant;    
    public Pod(Rectangle coordinatePod){
        this.coordinatePod = coordinatePod;
    }

    public Rectangle getCoordinate(){
        return this.coordinatePod;
    }

    public void setPlanted(Plant plant){
        this.isPlanted = true;
        this.plant = plant;
    }

    public boolean getisPlanted(){
        return this.isPlanted;
    }

    public Plant gePlant(){
        return this.plant;
    }
}
