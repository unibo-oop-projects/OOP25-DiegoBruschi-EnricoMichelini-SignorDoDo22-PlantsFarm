package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.Rarity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlantTest {
    
    @Test
    void testPlantCreation() {
        Plant plant = new Plant(PlantType.CARROT);
        assertTrue(plant.needsWater());
        assertTrue(plant.getName() == "Carrot");
        assertTrue(plant.getRarity() == Rarity.COMMON);
        assertTrue(plant.getType() == PlantType.CARROT);
        assertTrue(plant.getGrowthStage() == 0);
    }

    @Test
    void testPlantWatering() {
        Plant plant = new Plant(PlantType.CARROT);
        long now = System.currentTimeMillis();
        plant.water(now);
        assertTrue(!plant.needsWater());
        plant.water(plant.getLastWateredTime() + Plant.WATER_TIME_COOLDOWN);
        assertTrue(!plant.needsWater());
    }

    @Test
    void testPlantGrowth() {
        Plant plant = new Plant(PlantType.CARROT);
        plant.setCurrentStageTime(plant.getCurrentStageTime() + 35_000L);
        plant.increaseGrowthStage(System.currentTimeMillis());
        assertTrue(plant.getGrowthStage() == 0);
        plant.water(System.currentTimeMillis());
        plant.increaseGrowthStage(System.currentTimeMillis());
        assertTrue(plant.getGrowthStage() == 1);
        assertTrue(plant.getMaxGrowthStage() == 3);
        plant.setGrowthStage(3);
        assertTrue(plant.getGrowthStage() == plant.getMaxGrowthStage());
    }
    
    @Test
    void testPlantHoeing() {
        Plant plant = new Plant(PlantType.CARROT);
        plant.setGrowthStage(3);
        assertTrue(plant.getGrowthStage() == 3);
        assertTrue(plant.isMature());
        plant.harvest();
        assertTrue(plant.getHarvestedQuantity() >= 2 || plant.getHarvestedQuantity() <= 3);
    }
}
