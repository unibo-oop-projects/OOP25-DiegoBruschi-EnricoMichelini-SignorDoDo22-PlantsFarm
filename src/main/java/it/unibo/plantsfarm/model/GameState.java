package it.unibo.plantsfarm.model;

import it.unibo.plantsfarm.model.menu.Coin;
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
        this.shop = new Shop();
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
     * Returns the shop requests as a Map.
     *
     * @return A map of plant types and their quantities.
     */
    public Map<PlantType, Integer> getRequests() {
        return this.shop.generateRequests(this);
    }

    /**
     * Returns the shop instance.
     *
     * @return The shop model.
     */
    public Shop getShop() {
        return this.shop;
    }

    /**
     * Returns the player's amount of coins.
     *
     * @return The current coin balance.
     */
    public int getWallet() {
        return this.wallet.getValue();
    }

    /**
     * Adds coins to the wallet.
     *
     * @param amount The amount to add.
     */
    public void addCoins(final int amount) {
        this.wallet.addAmount(amount);
    }

    /**
     * Spends coins from the wallet if sufficient funds exist.
     *
     * @param amount The amount to spend.
     * @return True if successful, false otherwise.
     */
    public boolean spendCoins(final int amount) {
        return this.wallet.removeAmount(amount);
    }

    /**
     * Adds harvested items to storage.
     *
     * @param type   The plant type.
     * @param amount The quantity.
     */
    public void addHarvest(final PlantType type, final int amount) {
        this.storage.addItem(type, amount);
    }

    /**
     * Removes items from storage.
     *
     * @param type   The plant type.
     * @param amount The quantity.
     * @return True if successful.
     */
    public boolean removeHarvest(final PlantType type, final int amount) {
        return this.storage.removeItem(type, amount);
    }
}
