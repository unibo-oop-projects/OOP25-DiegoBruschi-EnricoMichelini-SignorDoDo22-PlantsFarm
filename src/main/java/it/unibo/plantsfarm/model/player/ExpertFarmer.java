package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public class ExpertFarmer extends AbstractPlayer {

    public ExpertFarmer() {
        super();
        this.speed = EXPERT_FARMER_SPEED;
    }
}
