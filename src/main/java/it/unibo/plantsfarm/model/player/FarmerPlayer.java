package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public final class FarmerPlayer extends AbstractPlayer {

    public FarmerPlayer() {
        super();
        this.speed = FARMER_SPEED;
    }
}
