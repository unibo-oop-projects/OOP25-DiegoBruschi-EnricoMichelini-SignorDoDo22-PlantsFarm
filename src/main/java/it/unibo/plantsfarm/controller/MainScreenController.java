package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.controller.menu.EncyclopediaController;
import it.unibo.plantsfarm.controller.menu.PauseMenuController;
import it.unibo.plantsfarm.controller.menu.ShopController;
import it.unibo.plantsfarm.controller.menu.StorageController;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.view.MainScreen;

/**
 * Manages the interactions on the Main Screen, acting as a central navigation hub 
 * for the Shop, Encyclopedia, Storage, and Pause menus.
 */
public final class MainScreenController {

    private final MainScreen view;

    /**
     * Initializes the Main Screen Controller and synchronizes the view with the current game state.
     *
     * @param gameState The current game state.
     */
    public MainScreenController(final GameState gameState) {
        this.view = new MainScreen();
        this.view.createMainScreen();

        setupListeners(gameState);
        updateView(gameState);
    }

    /**
     * Configures action listeners for all navigation buttons, 
     * delegating the menù logic to their respective controllers.
     *
     * @param gameState The game state to pass.
     */
    private void setupListeners(final GameState gameState) {

        // Shop Button
        this.view.attachShopListener(e -> {
            new ShopController(gameState, () -> updateView(gameState)).start();
            updateView(gameState);
        });

        // Encyclopedia Button
        this.view.attachEncyclopediaListener(e -> {
            new EncyclopediaController().start(gameState);
        });

        // Storage Button
        this.view.attachStorageListener(e -> {
            new StorageController(gameState).start();
        });

        // Exit Button
        this.view.attachExitListener(e -> {
            new PauseMenuController(this.view::close, gameState::resetGame).start();
        });
    }

    private void updateView(final GameState gameState) {
        this.view.updateCoinLabel(gameState.getWallet());
    }
}
