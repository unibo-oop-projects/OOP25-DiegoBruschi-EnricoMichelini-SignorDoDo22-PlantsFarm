package it.unibo.plantsfarm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.menu.Encyclopedia;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;

final class EncyclopediaTest {

    private final Encyclopedia encyclopedia = new Encyclopedia();
    private final Plant carrotInstance = new Plant(PlantType.CARROT);
    private final Plant begoniaInstance = new Plant(PlantType.BEGONIA);

    /**
     * Basic Test: adding plants and avoiding object duplicates.
     */
    @Test
    void testEncyclopediaManagement() {
        assertEquals(0, encyclopedia.numberPlants());
        assertTrue(encyclopedia.getPlants().isEmpty());

        encyclopedia.addPlant(carrotInstance);
        assertEquals(1, encyclopedia.numberPlants());
        assertTrue(encyclopedia.getPlants().contains(carrotInstance));

        encyclopedia.addPlant(carrotInstance);
        assertEquals(1, encyclopedia.numberPlants());

        encyclopedia.addPlant(begoniaInstance);
        assertEquals(2, encyclopedia.numberPlants());
    }

    /**
     * Test the getUnlockedEdiblePlants feature.
     */
    @Test
    void testFilters() {
        encyclopedia.addPlant(carrotInstance);
        encyclopedia.addPlant(begoniaInstance);

        carrotInstance.getType().unlock();

        assertTrue(encyclopedia.getUnlockedEdiblePlants().contains(carrotInstance));

        begoniaInstance.getType().unlock();

        assertFalse(encyclopedia.getUnlockedEdiblePlants().contains(begoniaInstance));
        assertEquals(1, encyclopedia.getNumberUnlockedEdiblePlants());
    }

    /**
     * Test the Unlock All feature.
     */
    @Test
    void testUnlockAll() {
        final Plant onion = new Plant(PlantType.ONION);
        final Plant monstera = new Plant(PlantType.MONSTERA);

        encyclopedia.addPlant(onion);
        encyclopedia.addPlant(monstera);

        encyclopedia.unlockAll();

        assertTrue(onion.isDiscovered());
        assertTrue(monstera.isDiscovered());
    }
}
