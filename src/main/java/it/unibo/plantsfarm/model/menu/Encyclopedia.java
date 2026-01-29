package it.unibo.plantsfarm.model.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.plantsfarm.controller.memory.DataMemory;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

import it.unibo.plantsfarm.model.plant.Plant;

/**
 * Represents the encyclopedia containing information about all plants in the game.
 * It shows only unlocked plants by the player.
 */
public final class Encyclopedia {

    private static final String FILE_NAME = "encyclopedia.txt";
    private static final String SEPARATOR = ":";
    private static final Logger LOGGER = Logger.getLogger(Encyclopedia.class.getName());

    private final List<Plant> plants;
    private final DataMemory memory;

    /**
     * Creates a new empty Encyclopedia.
     */
    public Encyclopedia() {
        this.plants = new ArrayList<>();
        this.memory = new DataMemory();
    }

    /**
     * Saves the current unlocked plants list to file.
     */
    public void save() {
        final StringBuilder sb = new StringBuilder();
        for (final PlantType type : PlantType.values()) {
            if (type.isDiscovered()) {
                sb.append(type.name()).append(SEPARATOR);
            }
        }
        try {
            this.memory.save(FILE_NAME, sb.toString());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving encyclopedia", e);
        }
    }

    /**
     * Resets encyclopedia.
     */
    public void reset() {
        for (final PlantType type : PlantType.values()) {
            type.lock();
        }
        PlantType.CARROT.unlock();
        save();
    }

    /**
     * Gets all plants in the encyclopedia.
     *
     * @return unmodifiable list of plants
     */
    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }

    /**
     * Adds a plant to the encyclopedia if it's not already present.
     *
     * @param plant The plant to add.
     */
    public void addPlant(final Plant plant) {
        if (!plants.contains(plant)) {
            plants.add(plant);
        }
    }

    /**
     * Returns the total number of plants in the encyclopedia.
     *
     * @return The number of plants.
     */
    public int numberPlants() {
        return plants.size();
    }

    /**
     * Unlocks all plants in the encyclopedia.
     */
    public void unlockAll() {
        for (final Plant plant : plants) {
            plant.getType().unlock();
        }
        save();
    }

    /**
     * Filters and returns only edible plants that have been discovered.
     *
     * @return A list of unlocked edible plants.
     */
    public List<Plant> getUnlockedEdiblePlants() {
        final List<Plant> unlockedEdiblePlantsList = new ArrayList<>();
        for (final Plant plant : plants) {
            if (plant.isEdible() && plant.isDiscovered()) {
                unlockedEdiblePlantsList.add(plant);
            }
        }
        return unlockedEdiblePlantsList;
    }

    /**
     * Counts the number of discovered edible plants.
     *
     * @return The number of discovered edible plants.
     */
    public int getNumberUnlockedEdiblePlants() {
        int total = 0;
        for (final Plant plant : plants) {
            if (plant.isEdible() && plant.isDiscovered()) {
                total++;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("=== Encyclopedia ===\n");
        for (final Plant p : plants) {
            stringBuilder.append(p.getName()).append('\n');
        }
        return stringBuilder.toString();
    }
}
