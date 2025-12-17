package it.unibo.plantsfarm;

import it.unibo.plantsfarm.controller.GameLoader;
import it.unibo.plantsfarm.model.Plant;
import java.util.List;
import java.util.logging.Logger;

/**
 * Main application entry point.
 */
public final class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
        // Private constructor to prevent instantiation
    }

    /**
     * Starts the application "plantsfarm".
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        final GameLoader gameLoader = new GameLoader();

        final List<Plant> plants = gameLoader.initializeGame();

        LOGGER.info("Game started: PlantsFarm.");
        LOGGER.info("Total plants loaded: " + plants.size());
    }
}
