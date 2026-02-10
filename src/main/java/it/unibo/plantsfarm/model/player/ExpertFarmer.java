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

    /**
     * Creates an {@code ExpertFarmer} with the given inventory.
     * An expert farmer has enhanced statistics compared to a standard farmer.
     *
     * @param inventory the inventory associated with this player
     * @throws NullPointerException if {@code inventory} is {@code null}
     */
    public ExpertFarmer(final ModelInventario inventory) {
        super(inventory);
        setSpeed(EXPERT_FARMER_SPEED);
    }
}
