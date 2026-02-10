package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.plant.Rarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PlantTest {

    private static final long ADDED_TIME = 35_000L;

    @Test
    void testPlantCreation() {
        final Plant plant = new Plant(PlantType.CARROT);
        assertTrue(plant.needsWater());
        assertEquals(plant.getName(), "Carrot");
        assertEquals(plant.getRarity(), Rarity.COMMON);
        assertEquals(plant.getType(), PlantType.CARROT);
        assertEquals(plant.getGrowthStage(), 0);
    }

    @Test
    void testPlantWatering() {
        final Plant plant = new Plant(PlantType.CARROT);
        final long now = System.currentTimeMillis();
        plant.water(now);
        assertFalse(plant.needsWater());
        plant.water(plant.getLastWateredTime() + Plant.WATER_TIME_COOLDOWN);
        assertFalse(plant.needsWater());
    }

    @Test
    void testPlantGrowth() {
        final Plant plant = new Plant(PlantType.CARROT);
        plant.setCurrentStageTime(plant.getCurrentStageTime() + ADDED_TIME);
        plant.increaseGrowthStage(System.currentTimeMillis());
        assertEquals(plant.getGrowthStage(), 0);
        plant.water(System.currentTimeMillis());
        plant.increaseGrowthStage(System.currentTimeMillis());
        assertEquals(plant.getGrowthStage(), 1);
        assertEquals(plant.getMaxGrowthStage(), 3);
        plant.setGrowthStage(3);
        assertEquals(plant.getGrowthStage(), plant.getMaxGrowthStage());
    }

    @Test
    void testPlantHoeing() {
        final Plant plant = new Plant(PlantType.CARROT);
        plant.setGrowthStage(3);
        assertEquals(plant.getGrowthStage(), 3);
        assertTrue(plant.isMature());
        plant.harvest();
        assertTrue(plant.getHarvestedQuantity() >= 2 || plant.getHarvestedQuantity() <= 3);
    }
}
