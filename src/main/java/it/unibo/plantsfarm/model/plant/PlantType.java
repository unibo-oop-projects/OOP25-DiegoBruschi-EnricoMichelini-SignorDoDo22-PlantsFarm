package it.unibo.plantsfarm.model.plant;

/**
 * Represents the type of a plant with game statistics.
 * Uses HarvestInfo to distinguish between edible plants and ornamental ones.
 */
public enum PlantType {

    //EDIBLE PLANTS
    CARROT("Carrot", 3, 1, Rarity.COMMON, new HarvestInfo(10, 2, 3), null),
    ONION("Onion", 3, 1, Rarity.COMMON, new HarvestInfo(12, 2, 3), null),
    RADISH("Radish", 3, 1, Rarity.COMMON, new HarvestInfo(8, 4, 5), null),
    ZUCCHINI("Zucchini", 4, 2, Rarity.COMMON, new HarvestInfo(15, 1, 3), null),
    TOMATO("Tomato", 5, 2, Rarity.COMMON, new HarvestInfo(7, 3, 7), null),
    POTATO("Potato", 3, 1, Rarity.COMMON, new HarvestInfo(15, 2, 3), null),
    PEPPER("Pepper", 5, 2, Rarity.COMMON, new HarvestInfo(15, 2, 4), null),
    CORN("Corn", 5, 3, Rarity.COMMON, new HarvestInfo(20, 1, 3), null),
    EGGPLANT("Eggplant", 4, 2, Rarity.RARE, new HarvestInfo(30, 1, 3), null),
    APPLE("Apple", 5, 3, Rarity.RARE, new HarvestInfo(15, 5, 10), null),
    FIG("Fig", 6, 3, Rarity.RARE, new HarvestInfo(15, 3, 12), null),
    PUMPKIN("Pumpkin", 6, 3, Rarity.RARE, new HarvestInfo(35, 1, 3), null),
    CHERRY("Cherry", 5, 3, Rarity.RARE, new HarvestInfo(5, 10, 25), null),
    WATERMELON("Watermelon", 5, 2, Rarity.RARE, new HarvestInfo(15, 6, 12), null),
    MANGO("Mango", 5, 3, Rarity.EPIC, new HarvestInfo(50, 5, 8), null),
    AVOCADO("Avocado", 5, 3, Rarity.EPIC, new HarvestInfo(75, 3, 7), null),
    DRAGONFRUIT("DragonFruit", 5, 2, Rarity.EPIC, new HarvestInfo(100, 2, 4), null),
    ANANAS("Ananas", 5, 2, Rarity.EPIC, new HarvestInfo(100, 1, 1), null),
    PAPAYA("Papaya", 6, 3, Rarity.EPIC, new HarvestInfo(55, 2, 6), null),
    POMEGRANATE("Pomegranate", 7, 4, Rarity.LEGENDARY, new HarvestInfo(40, 7, 10), null),
    BUDDHAHAND("Buddha's Hand", 7, 4, Rarity.LEGENDARY, new HarvestInfo(100, 3, 6), null),

    //ORNAMENTAL PLANTS
    SNAPDRAGON("SnapDragon", 3, 0, Rarity.COMMON, null, new EffectInfo(PlantEffect.GROWTH_SPEED, 0.10)),
    BEGONIA("Begonia", 5, 0, Rarity.COMMON, null, new EffectInfo(PlantEffect.BIG_HARVEST, 0.10)),
    MONSTERA("Monstera", 4, 0, Rarity.RARE, null, new EffectInfo(PlantEffect.GROWTH_SPEED, 0.25)),
    BLEEDINGHEARTH("BleedingHearth", 4, 0, Rarity.RARE, null, new EffectInfo(PlantEffect.BIG_HARVEST, 0.25)),
    HIBISCUS("Hibiscus", 4, 0, Rarity.RARE, null, new EffectInfo(PlantEffect.GROWTH_SPEED, 0.25)),
    STRELITZIA("Strelitzia", 5, 0, Rarity.EPIC, null, new EffectInfo(PlantEffect.BIG_HARVEST, 0.50)),
    ORCHID("Orchid", 3, 0, Rarity.EPIC, null, new EffectInfo(PlantEffect.GROWTH_SPEED, 0.50)),
    NEPENTHES("Nepenthes", 4, 0, Rarity.LEGENDARY, null, new EffectInfo(PlantEffect.BIG_HARVEST, 1.0)),
    RAFFLESIA("Rafflesia", 3, 0, Rarity.LEGENDARY, null, new EffectInfo(PlantEffect.GROWTH_SPEED, 1.0));

    private final String name;
    private final int maxGrowthStage;
    private final int resetStage;
    private final Rarity rarity;
    private final HarvestInfo harvestInfo;
    private final EffectInfo effectInfo;
    private boolean isDiscovered;

    /**
     * Constructor for PlantType.
     *
     * @param name            The display name.
     * @param maxGrowthStage  The maximum growth stage.
     * @param resetStage      The stage to reset to after harvest.
     * @param rarity          The rarity level.
     * @param harvestInfo     Economic info, Null for ornamentals.
     * @param effectInfo      Passive effect info, Null for edibles.
     */
    PlantType(final String name, final int maxGrowthStage, final int resetStage, final Rarity rarity,
              final HarvestInfo harvestInfo, final EffectInfo effectInfo) {

        this.name = name;
        this.maxGrowthStage = maxGrowthStage;
        this.resetStage = resetStage;
        this.rarity = rarity;
        this.harvestInfo = harvestInfo;
        this.effectInfo = effectInfo;
        this.isDiscovered = false;
    }

    /**
     * Gets the name of the plant.
     *
     * @return The name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the max growth stage of the plant.
     *
     * @return The max growth stage.
     */
    public int getMaxGrowthStage() {
        return maxGrowthStage;
    }

    /**
     * Gets the stage the plant reverts to after harvest.
     *
     * @return The reset stage index.
     */
    public int getResetStage() {
        return resetStage;
    }

    /**
     * Gets the rarity of the plant.
     *
     * @return The Rarity value.
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Checks if the plant is edible.
     *
     * @return True if it has harvest info, false otherwise.
     */
    public boolean isEdible() {
        return harvestInfo != null;
    }

    /**
     * Gets the harvest info object.
     *
     * @return The HarvestInfo object, or null if ornamental.
     */
    public HarvestInfo getHarvestInfo() {
        return harvestInfo;
    }

    /**
     * Gets the effect info object.
     *
     * @return The EffectInfo object, or null if edible.
     */
    public EffectInfo getEffectInfo() {
        return effectInfo;
    }

    /**
     * Unlocks this plant type in the encyclopedia.
     */
    public void unlock() {
        this.isDiscovered = true;
    }

    /**
     * Locks this plant type.
     */
    public void lock() {
        this.isDiscovered = false;
    }

    /**
     * Checks if this plant type has been discovered.
     *
     * @return true if discovered, false otherwise.
     */
    public boolean isDiscovered() {
        return isDiscovered;
    }
}
