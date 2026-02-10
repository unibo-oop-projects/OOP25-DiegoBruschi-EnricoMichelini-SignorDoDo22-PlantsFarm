package it.unibo.plantsfarm.model.plant;

import java.io.Serializable;

/**
 * Represents a plant in the game.
 * Holds the dynamic state and pick static info from PlantType.
 */
public class Plant implements Serializable {

    public static final long WATER_REDUCTION_TIME = 10_000L;
    public static final long WATER_TIME_COOLDOWN = 15_000L;
    public static final long GROWTH_TIME = 30_000L;

    private static final long serialVersionUID = 2L;

    private final PlantType type;

    private final boolean isPlanted;
    private int harvestedQuantity;
    private long currentStageTime;
    private long lastWateredTime;
    private int growthStage;
    private boolean needsWater;
    private boolean watered;
    private long lastUpdate;
    private double harvestMultiplier;

    /**
     * Creates a new Plant based on a specific type.
     *
     * @param type The type of plant.
     */
    public Plant(final PlantType type) {
        this.type = type;
        this.growthStage = 0;
        this.needsWater = true;
        this.isPlanted = true;
        this.lastWateredTime = System.currentTimeMillis();
        this.lastUpdate = System.currentTimeMillis();
        this.harvestMultiplier = 1.0;
    }

    public final void increaseGrowthStage(final long now) {
        this.increaseGrowthStage(now, 1.0);
    }

    public final void increaseGrowthStage(final long now, final double multiplier) {
        final long growthTimeFromLastUpdate = now - lastUpdate;
        lastUpdate = now;

        if (!isMature() && watered && !needsWater) {
            currentStageTime += (long) (growthTimeFromLastUpdate * multiplier);

            if (currentStageTime >= GROWTH_TIME && growthStage < getMaxGrowthStage()) {
                currentStageTime = 0;
                watered = false;
                growthStage++;
            }
            
        }
    }

    /**
     * Waters the plant, upgrade its growth stage if possible.
     *
     *  @param now The current time in milliseconds.
     */
    public final void water(final Long now) {
        if (growthStage < type.getMaxGrowthStage() && needsWater) {
            this.lastWateredTime = System.currentTimeMillis();
            watered = true;
            needsWater = false;
            currentStageTime += WATER_REDUCTION_TIME;
        }
    }

    /**
     * Updates the water needs of the plant based on time.
     *
     * @param now The current time in milliseconds.
     */
    public final void updateNeedsWater(final Long now) {
        if (this.type.getMaxGrowthStage() > this.growthStage && now - this.lastWateredTime >= WATER_TIME_COOLDOWN) {
            this.needsWater = true;
            this.watered = false;
        }
    }

    public final boolean isHarvestable() {
        return isMature();
    }

    /**
     * Checks if the plant has reached its max growth.
     *
     * @return true if mature, false otherwise.
     */
    public final boolean isMature() {
        return growthStage >= (type.getMaxGrowthStage() - 1);
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
     * Sets the harvest multiplier for this plant.
     *
     * @param multiplier The multiplier to set.
     */
    public final void setHarvestMultiplier(final double multiplier) {
        this.harvestMultiplier = multiplier;
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
        if (isMature()) {
            growthStage = this.type.getResetStage();
            currentStageTime = 0;
            final int baseHarvest = type.getHarvestInfo().generateHarvest();
            return (int) (baseHarvest * this.harvestMultiplier);
        }
        return 0;
    }

    public final int getHarvestedQuantity() {
        return this.harvestedQuantity;
    }

    public final long getCurrentStageTime() {
        return this.currentStageTime;
    }

    public final void setCurrentStageTime(final long newTime) {
        this.currentStageTime = newTime;
    }

    public final long getLastWateredTime() {
        return this.lastWateredTime;
    }

    public final void setLastWateredTime(final long newTime) {
        this.lastWateredTime = newTime;
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

    public final void setGrowthStage(final int stage) {
        this.growthStage = stage;
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
     * Checks if the plant type has been discovered.
     *
     * @return true if discovered, false otherwise.
     */
    public final boolean isDiscovered() {
        return type.isDiscovered();
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
