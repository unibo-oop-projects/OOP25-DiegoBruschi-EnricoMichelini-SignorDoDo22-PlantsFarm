package it.unibo.plantsfarm.model.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Manages the business logic for the Shop.
 */
public final class Shop {

    private static final int MAX_REQUESTS = 3;
    private static final int MIN_MULTIPLIER = 3;
    private static final int MAX_MULTIPLIER = 7;

    private final Random random;
    private Map<PlantType, Integer> activeRequests;

    /**
     * Creates a new Shop.
     */
    public Shop() {
        this.random = new Random();
    }

    /**
     * Generates random sell requests based on unlocked edible plants.
     *
     * @param gameState The current state of the game.
     * @return A map of PlantType and the requested quantity.
     */
    public Map<PlantType, Integer> generateRequests(final GameState gameState) {
        if (this.activeRequests != null && !this.activeRequests.isEmpty()) {
            return Collections.unmodifiableMap(this.activeRequests);
        }

        final List<Plant> unlocked = gameState.getAllUnlockedEdiblePlants();
        final Map<PlantType, Integer> requests = new EnumMap<>(PlantType.class);

        if (unlocked.isEmpty()) {
            return requests;
        }

        final List<Plant> shuffled = new ArrayList<>(unlocked);
        Collections.shuffle(shuffled);
        final List<Plant> selected = shuffled.subList(0, Math.min(MAX_REQUESTS, shuffled.size()));

        for (final Plant plant : selected) {
            final int baseHarvest = plant.getType().getHarvestInfo().generateHarvest();
            final int multiplier = MIN_MULTIPLIER + random.nextInt(MAX_MULTIPLIER - MIN_MULTIPLIER + 1);
            final int totalQuantity = baseHarvest * multiplier;

            requests.put(plant.getType(), totalQuantity);
        }

        this.activeRequests = requests;
        return Collections.unmodifiableMap(requests);
    }

    /**
     * Tries to sell the requested items and adds coins to the wallet.
     *
     * @param gameState The current state of the game.
     * @param requests The map of plants and quantities requested.
     * @return The amount of coins earned, or 0 if the transaction failed.
     */
    public int sellProducts(final GameState gameState, final Map<PlantType, Integer> requests) {
        final Map<PlantType, Integer> storage = gameState.getStorageContents();

        for (final Map.Entry<PlantType, Integer> entry : requests.entrySet()) {
            final PlantType type = entry.getKey();
            final int requiredQty = entry.getValue();
            if (storage.getOrDefault(type, 0) < requiredQty) {
                return 0;
            }
        }

        int totalEarnings = 0;

        for (final Map.Entry<PlantType, Integer> entry : requests.entrySet()) {
            final PlantType type = entry.getKey();
            final int quantity = entry.getValue();

            gameState.removeHarvest(type, quantity);
            totalEarnings += type.getHarvestInfo().getSellPrice() * quantity;
        }

        gameState.addCoins(totalEarnings);
        this.activeRequests = null;

        return totalEarnings;
    }

    /**
     * Tries to buy a random plant item (TEST).
     * Checks if the player has enough coins.
     *
     * @param gameState The current state of the game.
     * @param cost      The cost of the item.
     * @return True if the item was bought, false if not enough money.
     */
    public boolean buyItem(final GameState gameState, final int cost) {
        if (!gameState.spendCoins(cost)) {
            return false;
        }

        List<Plant> availablePool = gameState.getAllUnlockedEdiblePlants();

        if (availablePool.isEmpty()) {
            availablePool = gameState.getAllPlants().stream()
                .filter(p -> p.getType().isEdible())
                .collect(Collectors.toList());
        }

        if (!availablePool.isEmpty()) {
            final Plant randomPlant = availablePool.get(random.nextInt(availablePool.size()));
            gameState.addHarvest(randomPlant.getType(), 1);
            return true;
        }

        return false;
    }
}
