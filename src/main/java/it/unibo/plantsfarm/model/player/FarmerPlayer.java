package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.api.Player;

/**
 * Standard player.
 */
public final class FarmerPlayer extends Player {

    /**
     * FarmerPlayer inizializzation.
     *
     * @param inventory the player inventory model
     * @param playersTypes set player type
     * @throws NullPointerException if {@code inventory} is {@code null}
     */
    public FarmerPlayer(final ModelInventario inventory, final PlayersTypes playersTypes) {
        super(inventory, playersTypes);
        setSpeed(FARMER_SPEED);
    }
}
