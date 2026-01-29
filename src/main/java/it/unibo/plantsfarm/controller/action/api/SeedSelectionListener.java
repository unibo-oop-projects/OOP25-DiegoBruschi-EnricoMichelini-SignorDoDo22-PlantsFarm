package it.unibo.plantsfarm.controller.action.api;

import it.unibo.plantsfarm.model.plant.PlantType;

@FunctionalInterface
public interface SeedSelectionListener {
    void onPlantSelected(PlantType plant);
}
