package it.unibo.plantsfarm.model;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.FERTILIZER;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.items.ItemsExpert;
import it.unibo.plantsfarm.model.items.ItemsFarmBase;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.plant.Rarity;
import it.unibo.plantsfarm.model.player.ExpertFarmer;


public class ItemsTest {

    @Test
    public void upgradeItem() {

        ItemsFarm hoe = new ItemsFarmBase(HOE);
        assertTrue(HOE == hoe.getTooltype());
        assertTrue(hoe.getRarityItem() == Rarity.COMMON);
        ItemsFarm fertilizer = new ItemsFarmBase(FERTILIZER);
        assertTrue(FERTILIZER == fertilizer.getTooltype());
        assertTrue(fertilizer.getRarityItem() == Rarity.COMMON);
        ItemsFarm watercan = new ItemsFarmBase(WATERCAN);
        assertTrue(WATERCAN == watercan.getTooltype());
        assertTrue(watercan.getRarityItem() == Rarity.COMMON);

        ItemsFarm item = new ItemsFarmBase(WATERCAN);
        int experienceBefore = item.getExperience();
        item.useItem();
        assertTrue(experienceBefore < item.getExperience());

        ItemsFarm itemExpert = new ItemsExpert(HOE);
        int levelBefore = itemExpert.getLevel();
        assertTrue(itemExpert.getRarityItem() == Rarity.LEGENDARY);
        itemExpert.upgrade();
        System.out.println(levelBefore);
        assertTrue(levelBefore == itemExpert.getLevel());
    }
}
