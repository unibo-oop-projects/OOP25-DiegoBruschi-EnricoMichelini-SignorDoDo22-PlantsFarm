package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

/**
 * Standard player.
 */
public final class FarmerPlayer extends AbstractPlayer {

    /**
     * FarmerPlayer inizializzation.
     *
     * @param inventory the player inventory model
     */
    public FarmerPlayer(final ModelInventario inventory) {
        super(inventory);
    }

}
