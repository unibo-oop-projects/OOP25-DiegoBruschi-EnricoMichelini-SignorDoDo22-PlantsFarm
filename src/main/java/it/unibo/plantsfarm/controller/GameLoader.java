package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.GameState;
import java.util.List;

/**
 * Manage the initialization of all game data.
 */
public class GameLoader {

    /**
     * Initializes the game by calling specific loaders.
     *
     * @return The initial GameState.
     */
    public final GameState initializeGame() {
        //Load Plants
        final PlantLoader plantLoader = new PlantLoader();
        final List<Plant> plants = plantLoader.loadPlants();

        //Load Player State

        //Load Map

        // Return the complete state
        return new GameState(plants);
    }
}
