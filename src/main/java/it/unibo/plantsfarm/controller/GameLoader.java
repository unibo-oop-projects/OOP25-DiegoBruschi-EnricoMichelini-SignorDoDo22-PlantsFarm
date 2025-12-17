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
        final PlantLoader plantLoader = new PlantLoader();
        final List<Plant> plants = plantLoader.loadPlants();

        //return new GameState(plants, player, map .....);
        return new GameState(plants);
    }
}
