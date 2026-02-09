package it.unibo.plantsfarm.model.inventario;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.plantsfarm.controller.player.Memory;
import it.unibo.plantsfarm.controller.player.SavePlayer;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.plant.Rarity;
import it.unibo.plantsfarm.view.utility.FileMemory;

/**
 * Model of the inventory: stores the player's tools/items inside a map.
 * Every item can be upgraded ( if the player is baseFarmer).
 *
 */
public final class ModelInventario {

    private final Map<Tooltype, ItemsFarm> inventario;
    Memory memory = new FileMemory(Path.of("saves"));
    private final SavePlayer palyerSaving =  new SavePlayer(memory, this);

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
        palyerSaving.load();
    }

    /**
     * Adds/Replaces an item in the inventory.
     *
     * @param type tool type key
     * @param item item instance
     */
    public void putItem(final Tooltype type, final ItemsFarm item) {
        Objects.requireNonNull(type, "type");
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
     * For see if a item is upgredable.
     *
     * @param tool type of tool
     *
     * @return true if the item is upgredable or false if it is not
     */
    public boolean isUpgredableItem(final Tooltype tool) {
        return getItem(tool).getExperience() >= getItem(tool).getExperienceForLevel()
               && getItem(tool).getLevel() <= getItem(tool).getMaxLevel();
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
        item.upgrade();
        palyerSaving.save();
        return true;
    }

    /**
     * If the item have been used the right way.
     * its experience grow.
     *
     * @param tool  tool action
     */
    public boolean useItem(final Tooltype tool, final Rarity rarityPlant) {
        if (rarityPlant != null && inventario.get(tool).getRarityItem() == rarityPlant) {
            this.inventario.get(tool).useItem();
            return true;
        }
        return false;
    }

    /**
     * If the item have been used the right way.
     * its experience grow.
     *
     * @param tool  tool action
     */
    public void useWater(final Tooltype tool) {
        this.inventario.get(tool).useItem();
    }

    /**
     *
     */
    public void applyUpgrade() {
        for (final Tooltype tool : Tooltype.values()) {
            inventario.get(tool).useItem();
        }
    }

    public void loadItem(final Tooltype tool, final int level) {

        ItemsFarm itemFarm = inventario.get(tool);
        itemFarm.setLevel(level);
    }


}
