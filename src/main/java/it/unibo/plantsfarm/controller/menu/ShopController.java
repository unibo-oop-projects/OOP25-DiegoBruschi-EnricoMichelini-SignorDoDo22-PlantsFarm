package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.menu.ShopScreen;

import java.util.List;

/**
 * Controller for the Shop feature.
 */
public final class ShopController {

    private final GameState gameState;
    private final ShopScreen view;

    /**
     * Creates a new ShopController.
     *
     * @param gameState The current game state.
     */
    public ShopController(final GameState gameState) {
        this.gameState = gameState;
        this.view = new ShopScreen();

        setupShop();
    }

    /**
     * Sets up the view with items and listeners.
     */
    private void setupShop() {
        final List<PlantType> requests = gameState.getRequests();

        for (final PlantType type : requests) {
            final int price = type.getHarvestInfo().getSellPrice();
            this.view.addSellItem(type.getName(), price);
        }

        this.view.setSellButton(e -> performSellAction());
        this.view.setBuyButtons(e -> buyItem());
    }

    /**
     * Manage the logic for selling the requested items.
     */
    private void performSellAction() {
    }

    /**
     * Manage the logic for buying an item.
     */
    private void buyItem() {
    }

    /**
     * Shows the shop window.
     */
    public void start() {
        this.view.show();
    }
}
