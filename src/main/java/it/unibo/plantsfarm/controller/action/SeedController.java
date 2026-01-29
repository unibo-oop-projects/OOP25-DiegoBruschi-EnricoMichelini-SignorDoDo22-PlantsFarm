package it.unibo.plantsfarm.controller.action;

import it.unibo.plantsfarm.controller.action.api.SeedSelectionListener;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.SeedView;

import java.awt.event.ActionListener;

public final class SeedController {

    private final SeedView view;
    private final SeedSelectionListener selectionListener;

    public SeedController(final SeedSelectionListener selectionListener, final boolean isEdible) {
        this.view = new SeedView(isEdible);
        this.selectionListener = selectionListener;

        if (isEdible) {
            loadEdiblePlants();
        } else {
            loadOrnamentalPlants();
        }
    }

    private void loadEdiblePlants() {
        for (final PlantType plant : PlantType.values()) {
            if (plant.isEdible()) {
                addToView(plant);
            }
        }
    }

    private void loadOrnamentalPlants() {
        for (final PlantType plant : PlantType.values()) {
            if (!plant.isEdible()) {
                addToView(plant);
            }
        }
    }

    private void addToView(final PlantType plant) {
        final ActionListener action = createListener(plant);
        this.view.addPlantButton(plant.getName(), plant.isDiscovered(), action);
    }

    private ActionListener createListener(final PlantType plant) {
        return e -> {
            if (plant.isDiscovered()) {
                selectionListener.onPlantSelected(plant);
                view.close();
            }
        };
    }

    public void start() {
        this.view.show();
    }
}

