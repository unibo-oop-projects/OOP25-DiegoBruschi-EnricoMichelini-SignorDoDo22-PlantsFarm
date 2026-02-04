package it.unibo.plantsfarm.model.player;

import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public final class FarmerPlayer extends AbstractPlayer {

    public FarmerPlayer(final ModelInventario inventory) {
        super(inventory);
        this.speed = FARMER_SPEED;
    }

}
