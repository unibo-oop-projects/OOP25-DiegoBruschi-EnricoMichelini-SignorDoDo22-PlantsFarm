package it.unibo.plantsfarm.model;

import it.unibo.plantsfarm.model.menu.Encyclopedia;
import it.unibo.plantsfarm.model.menu.Storage;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

import java.util.List;
import java.util.Map;

/**
 * Represents the current state of the game.
 */
public final class GameState {

    private final Encyclopedia encyclopedia;
    private final Storage storage;

    /**
     * Constructs a new GameState initialized with a list of plants.
     *
     * @param plants The list of plants to load into the internal encyclopedia.
     */
    public GameState(final List<Plant> plants) {
        this.encyclopedia = new Encyclopedia();
        this.storage = new Storage();
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

    /**
     * Returns the storage as a map.
     *
     * @return A map of plant types and their quantities.
     */
    public Map<PlantType, Integer> getStorageContents() {
        return this.storage.getAllItems();
    }

}
