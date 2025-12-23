package it.unibo.plantsfarm.model.menu;

import it.unibo.plantsfarm.model.plant.PlantType;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Manages the storage of harvested plants.
 */
public class Storage {

    private final Map<PlantType, Integer> items;

    /**
     * Creates a new empty Storage.
     * Initializes slots only for edible plants.
     */
    public Storage() {
        this.items = new EnumMap<>(PlantType.class);

        for (final PlantType type : PlantType.values()) {
            if (type.isEdible()) {
                items.put(type, 0);
            }
        }
    }

    /**
     * Adds a quantity of a specific plant to storage.
     *
     * @param type   The plant type.
     * @param amount The amount to add.
     */
    public void addProduct(final PlantType type, final int amount) {
        if (type == null || !type.isEdible() || amount <= 0) {
            return;
        }

        final int current = items.getOrDefault(type, 0);
        items.put(type, current + amount);
    }

    /**
     * Removes a quantity of a specific plant from storage.
     *
     * @param type   The plant type.
     * @param amount The amount to remove.
     * @return True if successful, false otherwise.
     */
    public boolean removeItem(final PlantType type, final int amount) {
        if (type == null || amount <= 0) {
            return false;
        }

        final int current = items.getOrDefault(type, 0);
        if (current >= amount) {
            items.put(type, current - amount);
            return true;
        }
        return false;
    }

    /**
     * Gets the quantity of a specific plant.
     *
     * @param type The plant type.
     * @return The quantity stored.
     */
    public int getQuantity(final PlantType type) {
        return items.getOrDefault(type, 0);
    }

    /**
     * Returns all stored items as an unmodifiable map.
     *
     * @return The items map.
     */
    public Map<PlantType, Integer> getAllItems() {
        return Collections.unmodifiableMap(items);
    }
}
