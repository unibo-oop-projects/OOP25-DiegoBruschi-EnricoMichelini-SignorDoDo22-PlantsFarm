package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.model.Plant;
import java.util.List;

/**
 * Orchestrates the initialization of all game data.
 */
public class GameLoader {

    /**
     * Initializes the game by calling specific loaders.
     *
     * @return A list containing all loaded plants.
     */
    public final List<Plant> initializeGame() {
        final PlantLoader plantLoader = new PlantLoader();
        return plantLoader.loadPlants();
    }
}
