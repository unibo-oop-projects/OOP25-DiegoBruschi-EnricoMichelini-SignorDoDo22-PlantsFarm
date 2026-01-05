/* PLANT
 * contains main information for the singular plant
 * DONE
 */

package it.unibo.Model;

public class Plant {

    private final PlantType type;
    private final boolean isEdible;
    private final String rarity;
    private final int maxGrowthStage;

    private int growthStage;
    private boolean needsWater;
    private boolean isPlanted;
    private boolean isDiscovered;

    public Plant(PlantType type, boolean isEdible, int maxGrowthStage, String rarity) {
        this.type = type;
        this.isEdible = isEdible;
        this.rarity = rarity;
        this.growthStage = 0;
        this.maxGrowthStage = maxGrowthStage;
        this.needsWater = false;
        this.isPlanted = false;
        this.isDiscovered = false;
    }

    public void plant() {
        if (!isPlanted) {
            isPlanted = true;
            needsWater = true;
        }
    }

    //TO IMPLEMENT
    public void water() {
        if (isPlanted && needsWater) {
            needsWater = false;
            if (growthStage < maxGrowthStage) {
                growthStage++;
            }
        }
    }

    public boolean isMature() {
        return growthStage >= maxGrowthStage;
    }


    public void unlock() {
        this.isDiscovered = true;
    }

    //Getters

    public PlantType getType() {
        return type;
    }

    public int getGrowthStage() { 
        return growthStage; 
    }

    public boolean isEdible() { 
        return isEdible; 
    }

    public boolean needsWater() { 
        return needsWater; 
    }

    public boolean isPlanted() { 
        return isPlanted; 
    }

    public boolean isDiscovered() { 
        return isDiscovered; 
    }

    public String getRarity() { 
        return rarity; 
    }

    public int getMaxGrowthStage() { 
        return maxGrowthStage; 
    }

    @Override
    public String toString() {
        return "Plant: " + "type=" + type +
        ", growthStage=" + growthStage + 
        ", isEdible=" + isEdible +
        ", rarity=" + rarity;
    }
}
