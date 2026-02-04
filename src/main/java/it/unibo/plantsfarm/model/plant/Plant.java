package it.unibo.plantsfarm.model.plant;

import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.PlantType.Rarity;

/**
 * Represents a plant in the game.
 * Holds the dynamic state and pick static info from PlantType.
 */
public class Plant {

    // Static info
    private static final long WATER_REDUCTION_TIME = 5_000L;
    private static final long WATER_TIME_COOLDOWN = 15_000L;
    private static final long GROWTH_TIME = 5_000L; //30000
    private final PlantType type;
    //private GameState gameState;

    // Dynamic info
    private int growthStage;
    private boolean needsWater;
    private boolean watered;
    private boolean fertilized;
    private boolean isPlanted;
    public long currentStageTime;
    public long lastWateredTime;

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
        this.currentStageTime = System.currentTimeMillis();
        this.lastWateredTime = System.currentTimeMillis();
        //this.gameState = gameState;
    }

    public final void increaseGrowthStage(final long now){
        if (!isMature()) {
            if (watered && System.currentTimeMillis() >= currentStageTime + GROWTH_TIME && growthStage < getMaxGrowthStage()) {
                currentStageTime = now;
                watered = false;
                growthStage++;
            }
        } else {

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
            currentStageTime -= WATER_REDUCTION_TIME;
        }
    }

    /**
     * Updates the water needs of the plant based on time.
     *
     * @param now The current time in milliseconds.
     */
    public final void updateNeedsWater(final Long now) {
        System.out.println("NeedsWater " + needsWater + "  -  Watered " + watered);
        if (this.type.getMaxGrowthStage() > this.growthStage) {
            if (now - this.lastWateredTime >= WATER_TIME_COOLDOWN) {
                this.needsWater = true;
            }
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
        return growthStage >= (type.getMaxGrowthStage()-1);
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
    public final void harvest() {
        if (!type.isEdible()) {
            System.out.println("Ornamentale");
        }
        growthStage = this.type.getResetStage();
        //gameState.addHarvest(type, type.getHarvestInfo().generateHarvest());
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
