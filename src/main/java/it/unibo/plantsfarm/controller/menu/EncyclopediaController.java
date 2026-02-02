package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.menu.Encyclopedia;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.view.menu.EncyclopediaScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Encyclopedia feature.
 */
public final class EncyclopediaController {

    private static final String NEXT_STAGE_COMMAND = "NEXT_STAGE";
    private final EncyclopediaScreen encyclopediaScreen;
    private final Encyclopedia encyclopedia;
    private Plant selectedPlant;
    private int currentStageIndex;

    /**
     * Creates the controller.
     */
    public EncyclopediaController() {
        this.encyclopediaScreen = new EncyclopediaScreen();
        this.encyclopedia = new Encyclopedia();
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

        this.encyclopediaScreen.show(names, unlockedStatus, listener, NEXT_STAGE_COMMAND);
    }

    private void selection(final String name, final GameState gameState) {
        for (final Plant plant : gameState.getAllPlants()) {
            if (plant.getName().equals(name)) {
                this.selectedPlant = plant;
                this.currentStageIndex = plant.getType().getMaxGrowthStage();
                final String description = this.encyclopedia.getPlantDescription(plant.getType());

                this.encyclopediaScreen.updateDetails(plant.getName(), description);
                this.encyclopediaScreen.setRarity(plant.getType().getRarity().name());
                this.encyclopediaScreen.updateStageImage(plant.getName(), this.currentStageIndex);
                break;
            }
        }
    }

    private void nextStage() {
        if (this.selectedPlant != null) {
            this.currentStageIndex++;
            final boolean exists = this.encyclopediaScreen.updateStageImage(this.selectedPlant.getName(), this.currentStageIndex);

            if (!exists) {
                this.currentStageIndex = 1;
                this.encyclopediaScreen.updateStageImage(this.selectedPlant.getName(), this.currentStageIndex);
            }
        }
    }
}
