package it.unibo.plantsfarm.model.items;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;

public class ItemsFarmBase implements ItemsFarm {
    private int minLevel = StatsItemBase.LEVEL_BEGIN;
    private int maxLevel = StatsItemBase.LEVEL_MAX;
    private int experience = StatsItemBase.EXPERIENCE_BEGIN;
    private int experienceForLevel = StatsItemBase.EXPERIENCE_FOR_UPGRADE;
    private int level = StatsItemBase.LEVEL_BEGIN;
    private final Tooltype type;

    public ItemsFarmBase(final Tooltype type) {
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
        System.out.println("STO UPGREDANDO level " +  level + " Experience " + experience + "EXPERIENCE FOR LEVEL " + experienceForLevel );
        level++;
        experience = StatsItemBase.EXPERIENCE_BEGIN;
        experienceForLevel += StatsItemBase.ADD_EXPERIENCE_FOR_UPGRADE;
    }

    @Override
    public void useItem() {
        experience += StatsItemBase.EXPERIENCE_FOR_ACTION;
        System.out.println("EXPERIENCE " + experience);
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

    @Override
    public int getExperience() {
       return this.experience;
    }

    @Override
    public int getExperienceForLevel() {
        return this.experienceForLevel;
    }

    private static final class StatsItemBase {
        private static final int EXPERIENCE_FOR_ACTION = 5;
        private static final int EXPERIENCE_FOR_UPGRADE = 30;
        private static final int ADD_EXPERIENCE_FOR_UPGRADE = 15;
        private static final int EXPERIENCE_BEGIN = 0;
        private static final int LEVEL_BEGIN = 0;
        private static final int LEVEL_MAX = 5;
    }
}
