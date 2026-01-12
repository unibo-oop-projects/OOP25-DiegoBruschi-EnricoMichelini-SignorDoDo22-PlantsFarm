package it.unibo.plantsfarm.model.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.plantsfarm.model.plant.Plant;

/**
 * Represents the encyclopedia containing information about all plants in the game.
 * It shows only unlocked plants by the player.
 */
public final class Encyclopedia {

    private final List<Plant> plants;

    /**
     * Creates a new empty Encyclopedia.
     */
    public Encyclopedia() {
        this.plants = new ArrayList<>();
    }

    /**
     * Gets all plants in the encyclopedia.
     *
     * @return unmodifiable list of plants
     */
    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }

    /**
     * Adds a plant to the encyclopedia if it's not already present.
     *
     * @param plant The plant to add.
     */
    public void addPlant(final Plant plant) {
        if (!plants.contains(plant)) {
            plants.add(plant);
        }
    }

    /**
     * Returns the total number of plants in the encyclopedia.
     *
     * @return The number of plants.
     */
    public int numberPlants() {
        return plants.size();
    }

    /**
     * Unlocks all plants in the encyclopedia.
     */
    public void unlockAll() {
        for (final Plant plant : plants) {
            plant.getType().unlock();
        }
    }

    /**
     * Filters and returns only edible plants that have been discovered.
     *
     * @return A list of unlocked edible plants.
     */
    public List<Plant> getUnlockedEdiblePlants() {
        final List<Plant> unlockedEdiblePlantsList = new ArrayList<>();
        for (final Plant plant : plants) {
            if (plant.isEdible() && plant.isDiscovered()) {
                unlockedEdiblePlantsList.add(plant);
            }
        }
        return unlockedEdiblePlantsList;
    }

    /**
     * Counts the number of discovered edible plants.
     *
     * @return The number of discovered edible plants.
     */
    public int getNumberUnlockedEdiblePlants() {
        int total = 0;
        for (final Plant plant : plants) {
            if (plant.isEdible() && plant.isDiscovered()) {
                total++;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("=== Encyclopedia ===\n");
        for (final Plant p : plants) {
            stringBuilder.append(p.getName()).append('\n');
        }
        return stringBuilder.toString();
    }
}
