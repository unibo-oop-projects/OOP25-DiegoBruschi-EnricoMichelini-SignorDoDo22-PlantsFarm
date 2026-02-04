package it.unibo.plantsfarm.model.items.api;

/**
 *
 */
public interface ItemsFarm {

    /**
     *
     * @return
     */
    int getIntegrity();

    /**
     *
     */
    void repair();

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

    /**
     *
     * @return
     */
    int getCostRepair();

    int getMaxIntegrity();

    int getMinIntegrity();

    int getMaxLevel();

    int getMinLevel();

    /**
     *
     */
    public enum Tooltype { HOE, WATERCAN, FERTILIZER };

}
