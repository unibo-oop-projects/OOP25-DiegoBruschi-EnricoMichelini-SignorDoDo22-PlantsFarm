package it.unibo.plantsfarm.model;

/**
 * Represents a generic plant in the game.
 */
public class Plant {

    private final String name; // Nuovo campo
    private final boolean isEdible;
    private final String rarity;
    private final int maxGrowthStage;

    private int growthStage;
    private boolean needsWater;
    private boolean isPlanted;
    private boolean isDiscovered;

    /**
     * Creates a new Plant.
     *
     * @param name           The name of the plant.
     * @param isEdible       Whether the plant is edible.
     * @param maxGrowthStage The maximum growth stage of the plant.
     * @param rarity         The rarity of the plant.
     */
    public Plant(final String name, final boolean isEdible, final int maxGrowthStage, final String rarity) {
        this.name = name;
        this.isEdible = isEdible;
        this.rarity = rarity;
        this.growthStage = 0;
        this.maxGrowthStage = maxGrowthStage;
        this.needsWater = false;
        this.isPlanted = false;
        this.isDiscovered = false;
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
            if (growthStage < maxGrowthStage) {
                growthStage++;
            }
        }
    }

    /**
     * Checks if the plant has reached its maximum growth.
     *
     * @return true if mature, false otherwise.
     */
    public final boolean isMature() {
        return growthStage >= maxGrowthStage;
    }

    /**
     * Unlocks the plant in the encyclopedia for tests.
     */
    public final void unlock() {
        this.isDiscovered = true;
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
     * Returns true or false if the plant is edible or not.
     *
     * @return true if edible.
     */
    public final boolean isEdible() {
        return isEdible;
    }

    /**
     * Checks if the plant needs water.
     *
     * @return true if the plant needs water.
     */
    public final boolean needsWater() {
        return needsWater;
    }

    /**
     * Checks if the plant is planted.
     *
     * @return true if planted.
     */
    public final boolean isPlanted() {
        return isPlanted;
    }

    /**
     * Checks if the plant has been discovered in the encyclopedia.
     *
     * @return true if discovered.
     */
    public final boolean isDiscovered() {
        return isDiscovered;
    }

    /**
     * Gets the rarity of the plant.
     *
     * @return the rarity string.
     */
    public final String getRarity() {
        return rarity;
    }

    /**
     * Gets the maximum growth stage.
     *
     * @return the max growth stage.
     */
    public final int getMaxGrowthStage() {
        return maxGrowthStage;
    }

    /**
     * Gets the name of the plant.
     *
     * @return the name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns a string representation of the plant.
     *
     * @return a description of the plant state.
     */
    @Override
    public String toString() {
        return "Plant: Pianta"
            + ", name=" + name
            + ", growthStage=" + growthStage
            + ", isEdible=" + isEdible
            + ", rarity=" + rarity;
    }
}
