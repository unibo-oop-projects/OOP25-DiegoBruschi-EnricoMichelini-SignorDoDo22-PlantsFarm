package it.unibo.plantsfarm.model.items;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.plant.Rarity;

public final  class ItemsExpert implements ItemsFarm {
    private int minLevel = StatsItemBase.LEVEL_MAX;
    private int maxLevel = StatsItemBase.LEVEL_MAX;
    private int experience = StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    private int experienceForLevel = StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    private int level = StatsItemBase.LEVEL_MAX;
    private final Tooltype type;
    private  Rarity itemRarity;

    public ItemsExpert(final Tooltype type) {
        this.type = type;
        this.itemRarity = Rarity.LEGENDARY;
    }

    @Override
    public Tooltype getTooltype() {
        return this.type;
    }

    @Override
    public void upgrade() {
        if (level >= maxLevel || experience < experienceForLevel) {
            return;
        }
        level++;
        this.experience = experience - StatsItemBase.EXPERIENCE_FOR_UPGRADE;
        experienceForLevel += StatsItemBase.ADD_EXPERIENCE_FOR_UPGRADE;
    }

    @Override
    public void useItem() {
        experience += StatsItemBase.EXPERIENCE_FOR_ACTION;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public int getMinLevel() {
        return this.minLevel;
    }

    public Rarity getRarityItem() {
        return this.itemRarity;
    }

    public void updateRarity(int level ){

        if (level < StatsItemBase.VAL_RARE){
            this.itemRarity = Rarity.COMMON;
        } else if (level >= StatsItemBase.VAL_RARE &&  level < StatsItemBase.VAL_EPIC) {
            this.itemRarity = Rarity.RARE;
        } else if (level >= StatsItemBase.VAL_EPIC &&  level < StatsItemBase.VAL_LEGENDARY) {
            this.itemRarity = Rarity.EPIC;
        } else {
            this.itemRarity = Rarity.LEGENDARY;
        }
    }

    private static final class StatsItemBase {
        private static final int EXPERIENCE_FOR_ACTION = 5;
        private static final int EXPERIENCE_FOR_UPGRADE = 30;
        private static final int ADD_EXPERIENCE_FOR_UPGRADE = 15;
        private static final int LEVEL_MAX = 10;
        private static final int VAL_RARE = 3;
        private static final int VAL_EPIC = 6;
        private static final int VAL_LEGENDARY = 10;
    }

    @Override
    public int getExperience() {
       return this.experience;
    }

    @Override
    public int getExperienceForLevel() {
        return this.experienceForLevel;
    }

}
