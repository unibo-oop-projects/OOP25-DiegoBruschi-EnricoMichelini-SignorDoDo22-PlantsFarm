package it.unibo.plantsfarm.model.inventario;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;

/**
 * Model of the inventory: stores the player's tools/items inside a map.
 * The internal map is never exposed as mutable; updates happen only through
 * the model's methods (repair/upgrade/etc.).
 */
public final class ModelInventario {

    private final Map<Tooltype, ItemsFarm> inventario;

    /**
     * Creates an empty inventory.
     */
    public ModelInventario() {
        this.inventario = new LinkedHashMap<>(Map.of());
    }

    /**
     * Creates an inventory initialized with the given items.
     *
     * @param initialItems initial items to add
     */
    public ModelInventario(final Map<Tooltype, ItemsFarm> initialItems) {
        this();
        Objects.requireNonNull(initialItems, "initialItems");
        initialItems.forEach(this::putItem);
    }

    /**
     * Adds/Replaces an item in the inventory.
     *
     * @param type tool type key
     * @param item item instance
     */
    public void putItem(final Tooltype type, final ItemsFarm item) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(item, "item");
        inventario.put(type, item);
    }

    /**
     * Returns a read-only snapshot of the inventory map.
     * External code cannot modify the internal map.
     *
     * @return unmodifiable copy of the inventory
     */
    public Map<Tooltype, ItemsFarm> getInventorySnapshot() {
        return Map.copyOf(inventario);
    }

    /**
     * Returns the item for a given tool type, if present.
     *
     * @param type tool type
     * @return optional item
     */
    public ItemsFarm getItem(final Tooltype type) {
        Objects.requireNonNull(type, "type");
        return inventario.get(type);
    }

    /**
     * Repairs the selected tool/item (model-side operation).
     *
     * @param type tool to repair
     * @return true if the item existed and was repaired
     */
    public boolean repair(final Tooltype type) {
        Objects.requireNonNull(type, "type");
        final ItemsFarm item = inventario.get(type);
        if (item == null) {
            return false;
        }
        item.repair(); // <-- adatta se il tuo metodo ha un altro nome
        return true;
    }

    /**
     * Upgrades the selected tool/item (model-side operation).
     *
     * @param type tool to upgrade
     * @return true if the item existed and was upgraded
     */
    public boolean upgrade(final Tooltype type) {
        Objects.requireNonNull(type, "type");
        final ItemsFarm item = inventario.get(type);
        if (item == null) {
            return false;
        }
        item.upgrade(); // <-- adatta se il tuo metodo ha un altro nome
        return true;
    }

    /**
     * Current durability/integrity for the selected item.
     *
     * @param type tool type
     * @return current value, or 0 if missing
     */
    public int getDurability(final Tooltype type) {
        Objects.requireNonNull(type, "type");
        final ItemsFarm item = inventario.get(type);
        return item.getIntegrity();
    }

    /**
     * Max durability/integrity for the selected item.
     *
     * @param type tool type
     * @return max value, or 0 if missing
     */
    public int getMaxDurability(final Tooltype type) {
        Objects.requireNonNull(type, "type");
        final ItemsFarm item = inventario.get(type);
        return item.getMaxIntegrity();
    }

    /**
     * Utility: checks whether the inventory contains the given tool.
     *
     * @param type tool type
     * @return true if present
     */
    public boolean contains(final Tooltype type) {
        Objects.requireNonNull(type, "type");
        return inventario.containsKey(type);
    }
}
