package it.unibo.plantsfarm.model.plant;

/**
 * Represents the type of a plant with game statistics.
 */
public enum PlantType {

    //EDIBLE PLANTS
    CARROT("Carrot", true, 3, Rarity.COMMON),
    ONION("Onion", true, 3, Rarity.COMMON),
    RADISH("Radish", true, 2, Rarity.COMMON),
    ZUCCHINI("Zucchini", true, 3, Rarity.COMMON),
    TOMATO("Tomato", true, 4, Rarity.COMMON),
    POTATO("Potato", true, 4, Rarity.COMMON),
    PEPPER("Pepper", true, 4, Rarity.COMMON),
    CORN("Corn", true, 5, Rarity.COMMON),
    EGGPLANT("Eggplant", true, 4, Rarity.RARE),
    APPLE("Apple", true, 4, Rarity.RARE),
    FIG("Fig", true, 4, Rarity.RARE),
    PUMPKIN("Pumpkin", true, 5, Rarity.RARE),
    CHERRY("Cherry", true, 5, Rarity.RARE),
    WATERMELON("Watermelon", true, 5, Rarity.RARE),
    MANGO("Mango", true, 6, Rarity.EPIC),
    AVOCADO("Avocado", true, 6, Rarity.EPIC),
    DRAGONFRUIT("Dragonfruit", true, 6, Rarity.EPIC),
    BUDDHAHAND("Buddha's Hand", true, 7, Rarity.EPIC),

    //ORNAMENTAL PLANTS
    BEGONIA("Begonia", false, 4, Rarity.COMMON),
    MONSTERA("Monstera", false, 4, Rarity.RARE),
    HIBISCUS("Hibiscus", false, 4, Rarity.RARE),
    STRELITZIA("Strelitzia", false, 4, Rarity.RARE),
    ORCHID("Orchid", false, 3, Rarity.EPIC),
    NEPENTHES("Nepenthes", false, 4, Rarity.EPIC);

    private final String name;
    private final boolean isEdible;
    private final int maxGrowthStage;
    private final Rarity rarity;

    /**
     * Constructor for PlantType.
     *
     * @param name The display name of the plant.
     * @param isEdible True if the plant is edible, false otherwise.
     * @param maxGrowthStage The maximum growth stage of the plant.
     * @param rarity The rarity level of the plant.
     */
    PlantType(final String name, final boolean isEdible, final int maxGrowthStage, final Rarity rarity) {
        this.name = name;
        this.isEdible = isEdible;
        this.maxGrowthStage = maxGrowthStage;
        this.rarity = rarity;
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
     * Checks if the plant is edible.
     *
     * @return True if edible, false otherwise.
     */
    public boolean isEdible() {
        return isEdible;
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
     * Defines the rarity levels for plants.
     */
    public enum Rarity {
        COMMON,
        RARE,
        EPIC
    }
}
