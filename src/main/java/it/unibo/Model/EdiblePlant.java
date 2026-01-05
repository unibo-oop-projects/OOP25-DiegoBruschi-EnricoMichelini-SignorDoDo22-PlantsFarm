/*This class extends the Plant class,
contains new parameters related to price,
and consists exclusively of edible plants or plants that produce an edible item.
*/

package it.unibo.Model;

import java.util.Random;

public class EdiblePlant extends Plant {
    
    private static final Random randomNumber = new Random();

    private final int lowerPrice;
    private final int maxPrice;
    private int currentPrice;

    public EdiblePlant(PlantType type, int maxGrowthStage, int lowerPrice, int maxPrice, String rarity) {
        super(type, true, maxGrowthStage, rarity);

        if (lowerPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Negative Price!");
        }
        if (lowerPrice > maxPrice) {
            throw new IllegalArgumentException("LowerPrice > MaxPrice!");
        }

        this.lowerPrice = lowerPrice;
        this.maxPrice = maxPrice;
        this.currentPrice = lowerPrice;
    }

    public int getLowerPrice() { 
        return lowerPrice; 
    }

    public int getMaxPrice() { 
        return maxPrice;
    }

    public int getCurrentPrice() { 
        return currentPrice; 
    }

    public void setCurrentPrice(int price) { 
        if (price < lowerPrice) {
            this.currentPrice = lowerPrice;
        } else if (price > maxPrice) {
            this.currentPrice = maxPrice;
        } else {
            this.currentPrice = price; 
        }
    }

    public void randomizePrice() {
        if (maxPrice > lowerPrice) {
            this.currentPrice = lowerPrice + randomNumber.nextInt((maxPrice - lowerPrice) + 1);
        } else {
            this.currentPrice = lowerPrice;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [Price: " + currentPrice + "]";
    }
}
