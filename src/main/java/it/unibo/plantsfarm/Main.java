package it.unibo.plantsfarm;

import it.unibo.plantsfarm.controller.GameLoader;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import java.util.logging.Logger;

/**
 * Main application entry point for PlantsFarm.
 */
public final class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
        //Utility class constructor
    }

    /**
     * Starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        final GameLoader gameLoader = new GameLoader();
        final GameState gameState = gameLoader.initializeGame();

        //TEST
        LOGGER.info("Total plants loaded: " + gameState.getAllPlants().size());
        for (final Plant p : gameState.getAllPlants()) {
            LOGGER.info(p.toString());
        }
    }
}
