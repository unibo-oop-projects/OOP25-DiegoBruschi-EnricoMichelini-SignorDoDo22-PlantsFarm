package it.unibo.plantsfarm.model.menu;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the business logic for the Shop.
 */
public final class Shop {

    private static final int GET_PLANT_COST = 100;
    private static final int MAX_REQUESTS = 3;

    private final GameState gameState;

    /**
     * Creates a new Shop.
     *
     * @param gameState The current state of the game to get unlocked edible plants.
     */
    public Shop(final GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Generates random sell requests based on unlocked edible plants.
     *
     * @return A list of up to 3 PlantTypes.
     */
    public List<PlantType> generateRequests() {

        final List<Plant> unlocked = gameState.getAllUnlockedEdiblePlants();
        final List<PlantType> shop = new ArrayList<>();

        for (final Plant plant : unlocked) {
            shop.add(plant.getType());
        }

        Collections.shuffle(shop);
        return shop.subList(0, Math.min(MAX_REQUESTS, shop.size()));
    }

    /**
     * Gets the cost to buy a random plant.
     *
     * @return The cost in coins.
     */
    public int getPlantCost() {
        return GET_PLANT_COST;
    }
}
