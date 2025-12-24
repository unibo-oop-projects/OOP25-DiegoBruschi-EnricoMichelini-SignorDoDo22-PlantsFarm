package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.menu.StorageScreen;

import java.util.Map;

/**
 * Controller for the Storage feature.
 */
public class StorageController {

    private final StorageScreen view;

    /**
     * Creates a new StorageController.
     *
     * @param gameState The current state of the game.
     */
    public StorageController(final GameState gameState) {
        this.view = new StorageScreen();
        final Map<PlantType, Integer> storage = gameState.getStorageContents();

        setupView(storage);
    }

    /**
     * Initializes the view with data.
     *
     * @param storage The map of items to display.
     */
    private void setupView(final Map<PlantType, Integer> storage) {
        for (final PlantType type : PlantType.values()) {
            if (type.isEdible()) {
                this.view.createStorageSlot(type.getName());
            }
        }
        for (final Map.Entry<PlantType, Integer> entry : storage.entrySet()) {
            final String plantName = entry.getKey().getName();
            final int quantity = entry.getValue();
            this.view.updateItemQuantity(plantName, quantity);
        }
    }

    /**
     * Opens the storage window.
     */
    public void showStorage() {
        this.view.show();
    }
}
