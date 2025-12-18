package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads the initial set of plants into the game.
 */
public class PlantLoader {

    /**
     * Loads plants directly from the PlantType enum.
     *
     * @return A list of initialized plants.
     */
    public List<Plant> loadPlants() {
        final List<Plant> plants = new ArrayList<>();
        for (final PlantType type : PlantType.values()) {
            plants.add(new Plant(type));
        }

        return plants;
    }
}
