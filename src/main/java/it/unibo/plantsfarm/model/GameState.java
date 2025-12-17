package it.unibo.plantsfarm.model;

import java.util.List;
import java.util.Collections;

/**
 * Represents the current state of the game, including plants, players, and map.
 */
public class GameState {

    private final List<Plant> plants;

    /**
     * @param plants The list of loaded plants.
     */
    public GameState(final List<Plant> plants) {
        this.plants = List.copyOf(plants);
    }

    /**
     * @return An unmodifiable list of plants.
     */
    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }
}
