package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.view.menu.EncyclopediaScreen;
import java.util.logging.Logger;

/**
 * Manage the Encyclopedia feature.
 */
public final class EncyclopediaController {

    private static final Logger LOGGER = Logger.getLogger(EncyclopediaController.class.getName());
    private final GameState gameState;
    private final EncyclopediaScreen view;

    /**
     * Creates the Encyclopedia controller.
     *
     * @param gameState The game model containing plants data.
     */
    public EncyclopediaController(final GameState gameState) {
        this.gameState = gameState;
        this.view = new EncyclopediaScreen();
    }

    /**
     * Opens the Encyclopedia view.
     */
    public void start() {
        LOGGER.info("Opening Encyclopedia Window. " + this.gameState);
        this.view.show();
    }
}
