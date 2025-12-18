package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.view.MainScreen;
import java.util.logging.Logger;

/**
 * Controller responsible for managing the Main Screen interactions.
 */
public final class MainScreenController {

    private static final Logger LOGGER = Logger.getLogger(MainScreenController.class.getName());
    private final GameState gameState;
    private final MainScreen view;

    /**
     * Creates the controller.
     *
     * @param gameState The current state of the game.
     */
    public MainScreenController(final GameState gameState) {
        this.gameState = gameState;
        this.view = new MainScreen();
    }

    /**
     * Initializes the view, sets up listeners, and displays the screen.
     */
    public void start() {
        this.view.createMainScreen();

        this.view.attachExitListener(e -> {
            LOGGER.info("Closing game with " + this.gameState.getAllPlants().size() + " plants active.");
            this.view.close(); 
        });
    }
}
