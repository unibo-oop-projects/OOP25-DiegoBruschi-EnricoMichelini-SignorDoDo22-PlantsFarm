package it.unibo.plantsfarm.model.menu;

import it.unibo.plantsfarm.controller.memory.DataMemory;
import it.unibo.plantsfarm.model.plant.PlantType;

import java.io.IOException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the storage of harvested plants.
 */
public class Storage {

    private static final String FILE_NAME = "storage.txt";
    private static final String PAIR_SEPARATOR = ";";
    private static final String VALUE_SEPARATOR = ":";
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

    private final Map<PlantType, Integer> items;
    private final DataMemory memory;

    /**
     * Creates a new empty Storage.
     * Initializes slots only for edible plants.
     */
    public Storage() {
        this.memory = new DataMemory();
        this.items = new EnumMap<>(PlantType.class);

        for (final PlantType type : PlantType.values()) {
            if (type.isEdible()) {
                items.put(type, 0);
            }
        }

        load();
    }

    /**
     * Adds a quantity of a specific plant to storage.
     *
     * @param type   The plant type.
     * @param amount The amount to add.
     */
    public void addItem(final PlantType type, final int amount) {
        if (type == null || !type.isEdible() || amount <= 0) {
            return;
        }

        final int current = items.getOrDefault(type, 0);
        items.put(type, current + amount);

        save();
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
            save();
            return true;
        }
        return false;
    }

    /**
     * Resets the storage to zero and updates the save file.
     */
    public void reset() {
        for (final PlantType type : items.keySet()) {
            items.put(type, 0);
        }

        save();
    }

    /**
     * Loads storage data from file.
     */
    private void load() {
        try {
            final String data = this.memory.load(FILE_NAME);
            if (data == null || data.isEmpty()) {
                return;
            }

            final String[] pairs = data.split(PAIR_SEPARATOR);
            for (final String pair : pairs) {
                if (pair.contains(VALUE_SEPARATOR)) {
                    final String[] parts = pair.split(VALUE_SEPARATOR);
                    try {
                        final PlantType type = PlantType.valueOf(parts[0]);
                        final int quantity = Integer.parseInt(parts[1]);
                        if (items.containsKey(type)) {
                            items.put(type, quantity);
                        }
                    } catch (final IllegalArgumentException e) {
                        LOGGER.log(Level.WARNING, "Invalid storage data: " + pair, e);
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't load storage file", e);
        }
    }

    /**
     * Saves current storage data to file.
     */
    private void save() {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<PlantType, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey().name())
              .append(VALUE_SEPARATOR)
              .append(entry.getValue())
              .append(PAIR_SEPARATOR);
        }

        try {
            this.memory.save(FILE_NAME, sb.toString());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Could not save storage", e);
        }
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
