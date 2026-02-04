package it.unibo.plantsfarm.model.player;

import java.util.LinkedHashMap;
import java.util.Map;

import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;

public class ExpertFarmer extends AbstractPlayer {

    final Map<Tooltype, ItemsFarm> inventory = new LinkedHashMap<>();

    public ExpertFarmer(final ModelInventario inventory) {
        super(inventory);
        this.speed = EXPERT_FARMER_SPEED;
    }

}
