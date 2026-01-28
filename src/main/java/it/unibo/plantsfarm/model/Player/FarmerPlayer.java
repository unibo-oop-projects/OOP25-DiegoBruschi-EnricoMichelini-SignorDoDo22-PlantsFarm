package it.unibo.plantsfarm.model.player;

import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.Items.ItemHoe;
import it.unibo.plantsfarm.model.Items.ItemsFarmBase;
import it.unibo.plantsfarm.model.player.api.Player;

public final class FarmerPlayer extends Player  {
   
    private List<ItemsFarmBase> inventario = new LinkedList<>();
    private ItemsFarmBase hoe;
    private ItemsFarmBase water;

    public FarmerPlayer(){
        super();
        this.speed = 150;
        hoe = new ItemHoe();
    }

}
