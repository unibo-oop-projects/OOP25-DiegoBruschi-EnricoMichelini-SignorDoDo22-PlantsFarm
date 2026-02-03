package it.unibo.plantsfarm.controller.loader;

import it.unibo.plantsfarm.controller.MainScreenController;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;

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
        final GameState gameState = new GameState(plants);
        final ImplControllerGamePanel gamePanel = new ImplControllerGamePanel(gameState);
        gamePanel.addView();
        gamePanel.start();
        
        //CONTROLLER
        new MainScreenController(gameState, gamePanel);

        return gameState;
    }
}
