package it.unibo.plantsfarm.model.player;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.player.api.FactoryPlayer;

public final class ImplFactoryPlayer implements FactoryPlayer {

    public AbstractPlayer createPlayer(final PlayersTypes request) {
        if (request.equals(FARMER)) {
            return new FarmerPlayer();

        } else if (request.equals(EXPERTFARMER)) {
            return new ExpertFarmer();
        } else {
            throw new IllegalArgumentException("Player Type : " + request + " NOT FOUND");
        }
    }
}
