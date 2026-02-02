package it.unibo.plantsfarm.model.player;

import java.util.List;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public final class FarmerPlayer extends AbstractPlayer {

    public FarmerPlayer(final List<ItemsFarm> inventory) {
        super(inventory);
        this.speed = FARMER_SPEED;
    }

}
