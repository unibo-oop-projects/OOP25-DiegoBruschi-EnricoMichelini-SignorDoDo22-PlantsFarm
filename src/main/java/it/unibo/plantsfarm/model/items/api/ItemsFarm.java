package it.unibo.plantsfarm.model.items.api;

/**
 *
 */
public interface ItemsFarm {

    /**
     *
     * @return
     */
    Tooltype getTooltype();

    /**
     *
     */
    void upgrade();

    /**
     *
     */
    void useItem();

    /**
     *
     * @return
     */
    int getLevel();

    int getExperience();

    int getExperienceForLevel();

    int getMaxLevel();

    int getMinLevel();

    /**
     *
     */
    public enum Tooltype { HOE, WATERCAN, FERTILIZER };

}
