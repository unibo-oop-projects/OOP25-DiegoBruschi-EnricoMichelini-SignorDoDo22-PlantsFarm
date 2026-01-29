package it.unibo.plantsfarm.model.player;

import java.util.LinkedList;
import java.util.List;
import it.unibo.plantsfarm.model.items.ItemsFarmBase;
import it.unibo.plantsfarm.model.player.api.Player;


public class ExpertFarmer extends Player{
    
    private List<ItemsFarmBase> inventario = new LinkedList<>();
    public ExpertFarmer(){
        super();
        this.speed = 250;
        
    }
}
