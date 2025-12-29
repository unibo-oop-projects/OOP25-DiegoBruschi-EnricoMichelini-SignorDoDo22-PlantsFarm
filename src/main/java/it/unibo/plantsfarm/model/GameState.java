package it.unibo.plantsfarm.model;

import it.unibo.plantsfarm.model.menu.Encyclopedia;
import it.unibo.plantsfarm.model.menu.Storage;
import it.unibo.plantsfarm.model.menu.Shop;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

import java.util.List;
import java.util.Map;

/**
 * Represents the current state of the game.
 */
public final class GameState {

    private static final int INITIAL_COINS = 250;

    private final Encyclopedia encyclopedia;
    private final Storage storage;
    private final Shop shop;
    private final Coin wallet;

    /**
     * Constructs a new GameState initialized with a list of plants.
     *
     * @param plants The list of plants to load into the internal encyclopedia.
     */
    public GameState(final List<Plant> plants) {
        this.encyclopedia = new Encyclopedia();
        this.storage = new Storage();
        this.shop = new Shop(this);
        this.wallet = new Coin(INITIAL_COINS);
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

    /**
     * Returns the storage as a list.
     *
     * @return A list of plant types and their quantities.
     */
    public List<PlantType> getRequests() {
        return this.shop.generateRequests();
    }

    /**
     * Returns the player's amount of coins.
     *
     * @return The current coin balance.
     */
    public int getWallet() {
        return this.wallet.getValue();
    }
}
