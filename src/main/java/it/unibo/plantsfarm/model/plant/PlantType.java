package it.unibo.plantsfarm.model.plant;

/**
 * Represents the type of a plant with game statistics.
 * Uses HarvestInfo to distinguish between edible plants and ornamental ones.
 */
public enum PlantType {

    //EDIBLE PLANTS
    CARROT("Carrot", 3, Rarity.COMMON, new HarvestInfo(10, 2, 3)),
    ONION("Onion", 3, Rarity.COMMON, new HarvestInfo(12, 2, 3)),
    RADISH("Radish", 3, Rarity.COMMON, new HarvestInfo(8, 4, 5)),
    ZUCCHINI("Zucchini", 4, Rarity.COMMON, new HarvestInfo(15, 1, 3)),
    TOMATO("Tomato", 5, Rarity.COMMON, new HarvestInfo(7, 3, 7)),
    POTATO("Potato", 3, Rarity.COMMON, new HarvestInfo(15, 2, 3)),
    PEPPER("Pepper", 5, Rarity.COMMON, new HarvestInfo(15, 2, 4)),
    CORN("Corn", 5, Rarity.COMMON, new HarvestInfo(20, 1, 3)),
    EGGPLANT("Eggplant", 4, Rarity.RARE, new HarvestInfo(30, 1, 3)),
    APPLE("Apple", 5, Rarity.RARE, new HarvestInfo(15, 5, 10)),
    FIG("Fig", 6, Rarity.RARE, new HarvestInfo(15, 3, 12)),
    PUMPKIN("Pumpkin", 6, Rarity.RARE, new HarvestInfo(35, 1, 3)),
    CHERRY("Cherry", 5, Rarity.RARE, new HarvestInfo(5, 10, 25)),
    WATERMELON("Watermelon", 5, Rarity.RARE, new HarvestInfo(15, 6, 12)),
    MANGO("Mango", 5, Rarity.EPIC, new HarvestInfo(50, 5, 8)),
    AVOCADO("Avocado", 5, Rarity.EPIC, new HarvestInfo(75, 3, 7)),
    DRAGONFRUIT("Dragonfruit", 4, Rarity.EPIC, new HarvestInfo(100, 2, 4)),
    BUDDHAHAND("Buddha's Hand", 7, Rarity.LEGENDARY, new HarvestInfo(100, 3, 6)),

    //ORNAMENTAL PLANTS
    BEGONIA("Begonia", 5, Rarity.COMMON, null),
    MONSTERA("Monstera", 4, Rarity.RARE, null),
    HIBISCUS("Hibiscus", 4, Rarity.RARE, null),
    STRELITZIA("Strelitzia", 5, Rarity.RARE, null),
    ORCHID("Orchid", 3, Rarity.EPIC, null),
    NEPENTHES("Nepenthes", 4, Rarity.LEGENDARY, null);

    private final String name;
    private final int maxGrowthStage;
    private final Rarity rarity;
    private final HarvestInfo harvestInfo;
    private boolean isDiscovered;

    /**
     * Constructor for PlantType.
     *
     * @param name            The display name.
     * @param maxGrowthStage  The maximum growth stage.
     * @param rarity          The rarity level.
     * @param harvestInfo     Economic info, Null for ornamentals.
     */
    PlantType(final String name, final int maxGrowthStage, final Rarity rarity, final HarvestInfo harvestInfo) {
        this.name = name;
        this.maxGrowthStage = maxGrowthStage;
        this.rarity = rarity;
        this.harvestInfo = harvestInfo;
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

    /**
     * Defines the rarity levels for plants.
     */
    public enum Rarity {
        COMMON,
        RARE,
        EPIC,
        LEGENDARY
    }
}
