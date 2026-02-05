package it.unibo.plantsfarm.model.plant;

/**
 * Encapsulates the economic and harvest data for a edible plant.
 */
public class HarvestInfo {

    private final int sellPrice;
    private final int minItem;
    private final int maxItem;

    /**
     * Creates a new HarvestInfo profile.
     *
     * @param sellPrice The value in coins of a single item of this plant.
     * @param minItem  The minimum quantity obtained when harvesting.
     * @param maxItem  The maximum quantity obtained when harvesting.
     */
    public HarvestInfo(final int sellPrice, final int minItem, final int maxItem) {
        this.sellPrice = sellPrice;
        this.minItem = minItem;
        this.maxItem = maxItem;
    }

    /**
     * Gets the selling price of the plant item.
     *
     * @return the price in coins.
     */
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * Generates a random number of items harvested between min and max.
     *
     * @return the number of items harvested.
     */
    public int generateHarvest() {
        if (minItem >= maxItem) {
            return minItem;
        }
        return minItem + (int) (Math.random() * (maxItem - minItem + 1));
    }
}
