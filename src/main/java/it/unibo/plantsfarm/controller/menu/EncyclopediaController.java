package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.view.menu.EncyclopediaScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Encyclopedia feature.
 */
public final class EncyclopediaController implements ActionListener {

    private static final String NEXT_STAGE_COMMAND = "NEXT_STAGE";
    private final GameState gameState;
    private final EncyclopediaScreen encyclopediaScreen;
    private Plant selectedPlant;
    private int currentStageIndex;

    /**
     * Creates the controller.
     *
     * @param gameState The current state of the game.
     */
    public EncyclopediaController(final GameState gameState) {
        this.gameState = gameState;
        this.encyclopediaScreen = new EncyclopediaScreen();
    }

    /**
     * Starts the encyclopedia.
     */
    public void start() {
        final List<String> names = new ArrayList<>();
        final List<Boolean> unlockedStatus = new ArrayList<>();

        for (final Plant plant : this.gameState.getAllPlants()) {
            names.add(plant.getName());
            unlockedStatus.add(plant.isDiscovered());
        }

        this.encyclopediaScreen.show(names, unlockedStatus, this, NEXT_STAGE_COMMAND);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        if (NEXT_STAGE_COMMAND.equals(command)) {
            nextStage();
        } else {
            selection(command);
        }
    }

    private void selection(final String name) {
        for (final Plant plant : this.gameState.getAllPlants()) {
            if (plant.getName().equals(name)) {
                this.selectedPlant = plant;
                this.currentStageIndex = 1;
                this.encyclopediaScreen.updateDetails(plant.getName(), plant.toString());
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
