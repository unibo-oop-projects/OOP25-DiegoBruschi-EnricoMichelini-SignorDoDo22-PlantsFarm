package it.unibo.plantsfarm.model.items;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;

public final  class ItemsExpert implements ItemsFarm {
    private int minLevel = StatsItemBase.LEVEL_MAX;
    private int maxLevel = StatsItemBase.LEVEL_MAX;
    private int experience = StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    private int experienceForLevel = StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    private int level = StatsItemBase.LEVEL_BEGIN;
    private final Tooltype type;

    public ItemsExpert(final Tooltype type) {
        this.type = type;
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

    private static final class StatsItemBase {
        private static final int EXPERIENCE_FOR_ACTION = 5;
        private static final int EXPERIENCE_FOR_UPGRADE = 30;
        private static final int ADD_EXPERIENCE_FOR_UPGRADE = 15;
        private static final int LEVEL_BEGIN = 0;
        private static final int LEVEL_MAX = 5;
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
