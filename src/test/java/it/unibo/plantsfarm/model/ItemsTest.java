package it.unibo.plantsfarm.model;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.FERTILIZER;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import it.unibo.plantsfarm.model.items.ItemsExpert;
import it.unibo.plantsfarm.model.items.ItemsFarmBase;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.plant.Rarity;

class ItemsTest {

    @Test
    void testItemCreation() {
        final ItemsFarm hoe = new ItemsFarmBase(HOE);
        final ItemsFarm fertilizer = new ItemsFarmBase(FERTILIZER);
        final ItemsFarm watercan = new ItemsFarmBase(WATERCAN);

        assertEquals(HOE, hoe.getTooltype());
        assertEquals(FERTILIZER, fertilizer.getTooltype());
        assertEquals(WATERCAN, watercan.getTooltype());

        assertEquals(Rarity.COMMON, hoe.getRarityItem());
        assertEquals(Rarity.COMMON, fertilizer.getRarityItem());
        assertEquals(Rarity.COMMON, watercan.getRarityItem());
    }

    @Test
    void testUseItemIncreaseExperience() {
        final ItemsFarm item = new ItemsFarmBase(WATERCAN);

        final int experienceBefore = item.getExperience();
        item.useItem();
        final int experienceAfter = item.getExperience();

        assertTrue(experienceAfter > experienceBefore);
    }

    @Test
    void testExpertItemIsLegendaryAndMaxLevel() {
        final ItemsFarm expertItem = new ItemsExpert(HOE);
        final int levelBefore = expertItem.getLevel();
        assertEquals(Rarity.LEGENDARY, expertItem.getRarityItem());
        expertItem.upgrade();
        assertEquals(levelBefore, expertItem.getLevel());
    }
}
