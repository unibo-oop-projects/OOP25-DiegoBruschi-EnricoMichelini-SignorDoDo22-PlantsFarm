package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.view.menu.EncyclopediaScreen;

/**
 * Manage the encyclopedia screen.
 */
public final class EncyclopediaController {

    private final GameState gameState;
    private final EncyclopediaScreen view;

    /**
     * Creates the Encyclopedia controller.
     * 
     * @param gameState The game model containing plants.
     */
    public EncyclopediaController(final GameState gameState) {
        this.gameState = gameState;
        this.view = new EncyclopediaScreen();
    }

    /**
     * Opens the Encyclopedia view.
     */
    public void start() {
        this.view.show();
    }
}