package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

/**
 * This class create The ExpertFarmer. It can be used for try everything in the game.
 * The items in this type of player are all upgraded.
 * The movement is faster than the Farmer.
 * It can be used for test the game.
 */
public final class ExpertFarmer extends AbstractPlayer {

    public ExpertFarmer(final ModelInventario inventory) {
        super(inventory);
    }

    @Override
    public void initStats() {
        this.speed = EXPERT_FARMER_SPEED;
    }

}
