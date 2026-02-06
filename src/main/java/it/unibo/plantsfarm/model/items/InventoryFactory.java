package it.unibo.plantsfarm.model.items;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;
import java.util.LinkedHashMap;
import java.util.Map;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.PlayersTypes;

/**
 * Create a Inventory based on the type of player.
 * ExpertPlayer offer the possibility to play the game like a "creative mode ", with all items upgraded.
 * Farmer represents the standard game experience.
 */
public final class InventoryFactory {

/**
 * Creates and initializes an inventory according to the given player type.
 *
 * @param typePlayer the type of player for which the inventory is created
 * @return a fully initialized ModellIventario
 * @throws IllegalArgumentException if the player type is not supported
 */
    public ModelInventario createInventory(final PlayersTypes typePlayer) {
        if (typePlayer == EXPERTFARMER) {
            final Map<Tooltype, ItemsFarm> items = new LinkedHashMap<>();
            items.put(Tooltype.FERTILIZER, new ItemsExpert(Tooltype.FERTILIZER));
            items.put(Tooltype.WATERCAN, new ItemsExpert(Tooltype.WATERCAN));
            items.put(Tooltype.HOE, new ItemsExpert(Tooltype.HOE));
            final ModelInventario inventory = new ModelInventario(items);
            return inventory;

        } else if (typePlayer == FARMER) {
            final Map<Tooltype, ItemsFarm> items = new LinkedHashMap<>();
            items.put(Tooltype.FERTILIZER, new ItemsFarmBase(Tooltype.FERTILIZER));
            items.put(Tooltype.WATERCAN, new ItemsFarmBase(Tooltype.WATERCAN));
            items.put(Tooltype.HOE, new ItemsFarmBase(Tooltype.HOE));
            final ModelInventario inventory = new ModelInventario(items);
            return inventory;

        } else {
            throw new IllegalArgumentException("TypePlayer not found ");
        }
    }
}
