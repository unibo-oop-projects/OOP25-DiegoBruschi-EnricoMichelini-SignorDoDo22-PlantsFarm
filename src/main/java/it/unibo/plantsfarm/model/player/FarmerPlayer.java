package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.player.api.Player;

public final class FarmerPlayer extends Player {

    public FarmerPlayer() {
        super();
        this.speed = FARMER_SPEED;
    }
}
