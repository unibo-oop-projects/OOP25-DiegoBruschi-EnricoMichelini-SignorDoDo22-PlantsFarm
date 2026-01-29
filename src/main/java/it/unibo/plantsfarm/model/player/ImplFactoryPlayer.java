package it.unibo.plantsfarm.model.player;

import static it.unibo.plantsfarm.model.player.PlayersTypes.*;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.player.api.FactoryPlayer;

public class ImplFactoryPlayer implements FactoryPlayer {   
    
    public Player createPlayer(PlayersTypes request) {
        if (request.equals(FARMER)) {
            return new FarmerPlayer();

        } else if (request.equals(EXPERTFARMER)) {
            return new ExpertFarmer();
        } else {
            throw new IllegalArgumentException("Player Type : " + request + " NOT FOUND");
        }
    }
}
