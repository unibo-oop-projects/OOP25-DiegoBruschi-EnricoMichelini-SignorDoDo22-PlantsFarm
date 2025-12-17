package it.unibo.plantsfarm.model.plant;

/**
 * A factory class to create plants using standard presets.
 */
public final class PlantFactory {

    /** Extra Short growth duration (2 stages). */
    public static final int GROWTH_SS = 2;
    /** Short growth duration (3 stages). */
    public static final int GROWTH_S = 3;
    /** Medium growth duration (5 stages). */
    public static final int GROWTH_M = 5;
    /** Medium-Long growth duration (6 stages). */
    public static final int GROWTH_MM = 6;
    /** Long growth duration (7 stages). */
    public static final int GROWTH_L = 7;
    /** Extra Long growth duration (8 stages). */
    public static final int GROWTH_LL = 8;

    /** Common rarity level. */
    public static final String COMMON = "Common";
    /** Rare rarity level. */
    public static final String RARE = "Rare";
    /** Epic rarity level. */
    public static final String EPIC = "Epic";

    private PlantFactory() {
        // Utility class constructor
    }

    /**
     * Creates a generic plant.
     *
     * @param name           The name of the plant.
     * @param isEdible       True if edible.
     * @param maxGrowthStage Max growth steps.
     * @param rarity         Rarity level.
     * @return a new Plant instance.
     */
    public static Plant createPlant(
        final String name, 
        final boolean isEdible, 
        final int maxGrowthStage, 
        final String rarity
    ) {
        return new Plant(name, isEdible, maxGrowthStage, rarity);
    }
}
