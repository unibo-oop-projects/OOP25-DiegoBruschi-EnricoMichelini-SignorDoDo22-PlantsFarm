package it.unibo.plantsfarm.model.player;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;

import it.unibo.plantsfarm.model.items.InventoryFactory;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.player.api.FactoryPlayer;

public final class ImplFactoryPlayer implements FactoryPlayer {

    private final InventoryFactory factoryInventory = new InventoryFactory();

    public AbstractPlayer createPlayer(final PlayersTypes request) {
        if (request.equals(FARMER)) {
            return new FarmerPlayer(factoryInventory.createInventory(request));

        } else if (request.equals(EXPERTFARMER)) {
            return new ExpertFarmer(factoryInventory.createInventory(request));
        } else {
            throw new IllegalArgumentException("Player Type : " + request + " NOT FOUND");
        }
    }
}
