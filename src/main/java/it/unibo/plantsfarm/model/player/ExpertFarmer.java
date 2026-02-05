package it.unibo.plantsfarm.model.player;

import java.util.LinkedHashMap;
import java.util.Map;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

/**
 * This class create The ExpertFarmer. It can be used for try everything in the game.
 * The items in this type of player are all upgraded.
 * The movement is faster than the Farmer.
 * It can be used for test the game.
 */
public final class ExpertFarmer extends AbstractPlayer {

    final Map<Tooltype, ItemsFarm> inventory = new LinkedHashMap<>();
    public ExpertFarmer(final ModelInventario inventory) {
        super(inventory);
        this.speed = EXPERT_FARMER_SPEED;
    }

}
