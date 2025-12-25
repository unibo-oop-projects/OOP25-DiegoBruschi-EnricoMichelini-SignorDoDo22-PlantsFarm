package it.unibo.plantsfarm.model.plant;

import it.unibo.plantsfarm.model.plant.PlantType.Rarity;

/**
 * Represents a plant in the game.
 * Holds the dynamic state and pick static info from PlantType.
 */
public class Plant {

    // Static info
    private final PlantType type;

    // Dynamic info
    private int growthStage;
    private boolean needsWater;
    private boolean isPlanted;
    private boolean isDiscovered;

    /**
     * Creates a new Plant based on a specific type.
     *
     * @param type The type of plant.
     */
    public Plant(final PlantType type) {
        this.type = type;
        this.growthStage = 0;
        this.needsWater = false;
        this.isPlanted = false;
        this.isDiscovered = true;
    }

    /**
     * Plants the seed if it hasn't been planted.
     */
    public final void plant() {
        if (!isPlanted) {
            isPlanted = true;
            needsWater = true;
        }
    }

    /**
     * Waters the plant, upgrade its growth stage if possible.
     */
    public final void water() {
        if (isPlanted && needsWater) {
            needsWater = false;
            if (growthStage < type.getMaxGrowthStage()) {
                growthStage++;
            }
        }
    }

    /**
     * Checks if the plant has reached its max growth.
     *
     * @return true if mature, false otherwise.
     */
    public final boolean isMature() {
        return growthStage >= type.getMaxGrowthStage();
    }

    /**
     * Unlocks the plant in the encyclopedia.
     */
    public final void unlock() {
        this.isDiscovered = true;
    }

    /**
     * Gets the selling value of a single unit of this plant.
     *
     * @return the value in coins, or 0 if ornamental.
     */
    public final int getSellValue() {
        if (!type.isEdible()) {
            return 0;
        }
        return type.getHarvestInfo().getSellPrice();
    }

    /**
     * Calculates the amount of items obtained from harvesting this plant.
     *
     * @return a random number between min and max yield, or 0 if ornamental.
     */
    public final int harvest() {
        if (!type.isEdible()) {
            return 0;
        }
        return type.getHarvestInfo().generateHarvest();
    }

    /**
     * Returns the type of this plant.
     *
     * @return The PlantType enum.
     */
    public final PlantType getType() {
        return type;
    }

    /**
     * Returns the current growth stage.
     *
     * @return the growth stage.
     */
    public final int getGrowthStage() {
        return growthStage;
    }

    /**
     * Returns true if the plant is edible.
     *
     * @return true if edible, false otherwise.
     */
    public final boolean isEdible() {
        return type.isEdible();
    }

    /**
     * Checks if the plant needs water.
     *
     * @return true if the plant needs water, false otherwise.
     */
    public final boolean needsWater() {
        return needsWater;
    }

    /**
     * Checks if the plant is planted.
     *
     * @return true if planted, false otherwise.
     */
    public final boolean isPlanted() {
        return isPlanted;
    }

    /**
     * Checks if the plant has been discovered in the encyclopedia.
     *
     * @return true if discovered, false otherwise.
     */
    public final boolean isDiscovered() {
        return isDiscovered;
    }

    /**
     * Gets the rarity of the plant.
     *
     * @return the rarity Enum.
     */
    public final Rarity getRarity() {
        return type.getRarity();
    }

    /**
     * Gets the max growth stage.
     *
     * @return the max growth stage.
     */
    public final int getMaxGrowthStage() {
        return type.getMaxGrowthStage();
    }

    /**
     * Gets the name of the plant.
     *
     * @return the name.
     */
    public final String getName() {
        return type.getName();
    }

    /**
     * Returns a string representation of the plant.
     *
     * @return a description of the plant.
     */
    @Override
    public String toString() {
        return "Plant: " + type.getName()
             + ", Stage: " + growthStage + "/" + type.getMaxGrowthStage()
             + ", Rarity: " + type.getRarity()
             + ", Value: " + getSellValue();
    }
}
