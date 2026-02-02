package it.unibo.plantsfarm.model.player;

import java.util.List;

import it.unibo.plantsfarm.model.Soil;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public final class FarmerPlayer extends AbstractPlayer {

    List<Soil> terreni;

    public FarmerPlayer() {
        super();
        this.speed = FARMER_SPEED;
    }
}
