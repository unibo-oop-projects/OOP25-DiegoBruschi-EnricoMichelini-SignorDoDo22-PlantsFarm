package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.menu.api.Encyclopedia;
import it.unibo.plantsfarm.model.menu.impl.EncyclopediaImpl;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.view.menu.api.EncyclopediaScreen;
import it.unibo.plantsfarm.view.menu.impl.EncyclopediaScreenImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the logic for the Encyclopedia feature, showing plant selection, 
 * description, and growth stages.
 */
public final class EncyclopediaController {

    private static final String NEXT_STAGE_COMMAND = "NEXT_STAGE";
    private final EncyclopediaScreen view;
    private final Encyclopedia encyclopedia;
    private Plant selectedPlant;
    private int currentStageIndex;

    /**
     * Creates the controller.
     */
    public EncyclopediaController() {
        this.view = new EncyclopediaScreenImpl();
        this.encyclopedia = new EncyclopediaImpl();
    }

    /**
     * Starts the encyclopedia.
     *
     * @param gameState The current state of the game.
     */
    public void start(final GameState gameState) {
        final List<String> names = new ArrayList<>();
        final List<Boolean> unlockedStatus = new ArrayList<>();

        for (final Plant plant : gameState.getAllPlants()) {
            names.add(plant.getName());
            unlockedStatus.add(plant.isDiscovered());
        }

        final ActionListener listener = (ActionEvent e) -> {
            final String command = e.getActionCommand();
            if (NEXT_STAGE_COMMAND.equals(command)) {
                nextStage();
            } else {
                selection(command, gameState);
            }
        };

        this.view.show(names, unlockedStatus, listener, NEXT_STAGE_COMMAND);
    }

    /**
     * Manages the selection of a specific plant, updating the details and images in the view.
     * 
     * @param name The name of the selected plant.
     * @param gameState The current state of the game, used to find the selected plant
     */
    private void selection(final String name, final GameState gameState) {
        for (final Plant plant : gameState.getAllPlants()) {
            if (plant.getName().equals(name)) {
                this.selectedPlant = plant;
                this.currentStageIndex = plant.getType().getMaxGrowthStage();
                final String description = this.encyclopedia.getPlantDescription(plant.getType());

                this.view.updateDetails(plant.getName(), description);
                this.view.setRarity(plant.getType().getRarity().name());
                this.view.updateStageImage(plant.getName(), this.currentStageIndex);
                break;
            }
        }
    }

    /**
     * Shows the next growth stage image for the current selected plant.
     */
    private void nextStage() {
        if (this.selectedPlant != null) {
            this.currentStageIndex++;
            final boolean exists = this.view.updateStageImage(this.selectedPlant.getName(), this.currentStageIndex);

            if (!exists) {
                this.currentStageIndex = 1;
                this.view.updateStageImage(this.selectedPlant.getName(), this.currentStageIndex);
            }
        }
    }
}
