package it.unibo.plantsfarm.model.items;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;
import java.util.LinkedHashMap;
import java.util.Map;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.PlayersTypes;


public class InventoryFactory {

    public ModelInventario createInventory(PlayersTypes typePlayer) {

        if (typePlayer == EXPERTFARMER) {
            Map<Tooltype, ItemsFarm> items = new LinkedHashMap<>();
            items.put(Tooltype.FERTILIZER, new ItemsExpert(Tooltype.FERTILIZER));
            items.put(Tooltype.WATERCAN, new ItemsExpert(Tooltype.WATERCAN));
            items.put(Tooltype.HOE, new ItemsExpert(Tooltype.HOE));
            ModelInventario inventory = new ModelInventario(items);
            return inventory;

        }else if (typePlayer == FARMER) {
            Map<Tooltype, ItemsFarm> items = new LinkedHashMap<>();
            items.put(Tooltype.FERTILIZER, new ItemsFarmBase(Tooltype.FERTILIZER));
            items.put(Tooltype.WATERCAN, new ItemsFarmBase(Tooltype.WATERCAN));
            items.put(Tooltype.HOE, new ItemsFarmBase(Tooltype.HOE));
            ModelInventario inventory = new ModelInventario(items);
            return inventory;

        } else {
            throw new IllegalArgumentException("TypePlayer not found ");
        }
    }
}

