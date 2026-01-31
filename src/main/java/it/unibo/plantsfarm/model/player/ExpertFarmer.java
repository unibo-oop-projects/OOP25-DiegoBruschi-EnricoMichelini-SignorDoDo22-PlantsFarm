package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.player.api.Player;

public class ExpertFarmer extends Player {

    public ExpertFarmer() {
        super();
        this.speed = EXPERT_FARMER_SPEED;
    }
}
