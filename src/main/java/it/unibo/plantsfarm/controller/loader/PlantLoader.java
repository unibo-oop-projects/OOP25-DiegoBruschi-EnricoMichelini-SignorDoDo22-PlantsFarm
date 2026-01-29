package it.unibo.plantsfarm.controller.loader;

import it.unibo.plantsfarm.controller.memory.DataMemory;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loads the initial set of plants into the game.
 */
public class PlantLoader {

    private static final String FILE_NAME = "encyclopedia.txt";
    private static final String SEPARATOR = ":";
    private static final Logger LOGGER = Logger.getLogger(PlantLoader.class.getName());

    /**
     * Loads plants directly from the PlantType enum.
     *
     * @return A list of initialized plants.
     */
    public List<Plant> loadPlants() {
        final DataMemory memory = new DataMemory();
        String loadedData = null;

        try {
            loadedData = memory.load(FILE_NAME);
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't load encyclopedia file", e);
        }

        if (loadedData != null && !loadedData.isEmpty()) {
            loadFromSave(loadedData);
        } else {
            loadDefaults();
            saveCurrentState(memory);
        }

        final List<Plant> plants = new ArrayList<>();
        for (final PlantType type : PlantType.values()) {
            plants.add(new Plant(type));
        }

        return plants;
    }

    private void loadFromSave(final String data) {
        final String[] unlockedNames = data.split(SEPARATOR);
        for (final String name : unlockedNames) {
            try {
                final PlantType type = PlantType.valueOf(name);
                type.unlock();
            } catch (final IllegalArgumentException e) {
                LOGGER.log(Level.WARNING, "Invalid plant name: " + name, e);
            }
        }
    }

    private void loadDefaults() {
        PlantType.CARROT.unlock();
    }

    private void saveCurrentState(final DataMemory memory) {
        final StringBuilder sb = new StringBuilder();
        for (final PlantType type : PlantType.values()) {
            if (type.isDiscovered()) {
                sb.append(type.name()).append(SEPARATOR);
            }
        }
        try {
            memory.save(FILE_NAME, sb.toString());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't save encyclopedia file", e);
        }
    }
}
