package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.menu.ShopScreen;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.Map;

/**
 * Controller for the Shop feature.
 */
public final class ShopController {

    private static final int DEFAULT_COST = 250;

    private final ShopScreen view;
    private final Runnable onTransactionListener;

    /**
     * Creates a new ShopController.
     *
     * @param gameState             The current game state.
     * @param onTransactionListener The action to run when coins change.
     */
    public ShopController(final GameState gameState, final Runnable onTransactionListener) {
        this.view = new ShopScreen();
        this.onTransactionListener = onTransactionListener;
        setupListeners(gameState);
        refreshRequests(gameState);
    }

    private void setupListeners(final GameState gameState) {
        this.view.setSellButton(e -> performSellAction(gameState));
        this.view.setBuyButtons(e -> buyItem(gameState, e));
    }

    private void refreshRequests(final GameState gameState) {
        this.view.resetRequestsPanel();

        final Map<PlantType, Integer> requests = gameState.getShop().generateRequests(gameState);

        for (final Map.Entry<PlantType, Integer> entry : requests.entrySet()) {
            final PlantType type = entry.getKey();
            final int quantity = entry.getValue();
            final int unitPrice = type.getHarvestInfo().getSellPrice();
            final int totalPrice = unitPrice * quantity;
            final String displayName = type.getName();

            this.view.addSellItem(displayName, quantity, totalPrice);
        }
    }

    private void performSellAction(final GameState gameState) {

        final Map<PlantType, Integer> requests = gameState.getShop().generateRequests(gameState);

        final int earnings = gameState.getShop().sellProducts(gameState, requests);

        if (earnings > 0) {
            showMessage("Sold!", "You earned " + earnings + " coins.");

            if (this.onTransactionListener != null) {
                this.onTransactionListener.run();
            }
            refreshRequests(gameState);
        } else {
            showMessage("Missing Items", "You don't have enough items in storage!");
        }
    }

    private void buyItem(final GameState gameState, final ActionEvent e) {
        int cost = DEFAULT_COST;

        if (e.getSource() instanceof JButton) {
            final JButton button = (JButton) e.getSource();
            final Object costProperty = button.getClientProperty("itemCost");

            if (costProperty instanceof Integer) {
                cost = (Integer) costProperty;
            }
        }

        final boolean purchased = gameState.getShop().buyItem(gameState, cost);

        if (purchased) {
            showMessage("Purchased", "You bought a mystery box for " + cost + " coins!");

            if (this.onTransactionListener != null) {
                this.onTransactionListener.run();
            }
        } else {
            showMessage("Insufficient Funds", "You need " + cost + " coins to buy this!");
        }
    }

    private void showMessage(final String title, final String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows the shop window.
     */
    public void start() {
        this.view.show();
    }
}
