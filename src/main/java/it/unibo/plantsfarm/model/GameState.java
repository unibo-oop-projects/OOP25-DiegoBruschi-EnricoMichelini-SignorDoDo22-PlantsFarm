package it.unibo.plantsfarm.model;

import it.unibo.plantsfarm.model.plant.Plant;
import java.util.List;

/**
 * Represents the current state of the game.
 */
public final class GameState {

    private final Encyclopedia encyclopedia;

    /**
     * Constructs a new GameState initialized with a list of plants.
     *
     * @param plants The list of plants to load into the internal encyclopedia.
     */
    public GameState(final List<Plant> plants) {
        this.encyclopedia = new Encyclopedia();
        for (final Plant p : plants) {
            this.encyclopedia.addPlant(p);
        }
    }

    /**
     * Returns the list of plants contained in encyclopedia.
     *
     * @return The list of plants.
     */
    public List<Plant> getAllPlants() {
        return encyclopedia.getPlants();
    }

    /**
     * Returns the list of edible plants contained in encyclopedia.
     *
     * @return The list of edible plants.
     */
    public List<Plant> getAllUnlockedEdiblePlants() {
        return encyclopedia.getUnlockedEdiblePlants();
    }
}
