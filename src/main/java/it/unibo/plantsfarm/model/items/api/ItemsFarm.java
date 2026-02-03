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
    void repairItem();

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

    /**
     *
     */
    public enum Tooltype { HOE, WATERCAN, FERTILIZER };

}
