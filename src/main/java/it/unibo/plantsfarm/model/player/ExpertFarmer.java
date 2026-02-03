package it.unibo.plantsfarm.model.player;

import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public class ExpertFarmer extends AbstractPlayer {

    List<ItemsFarm> inventory = new LinkedList<>();

    public ExpertFarmer(final List<ItemsFarm> inventory) {
        super(inventory);
        this.speed = EXPERT_FARMER_SPEED;
    }

}
