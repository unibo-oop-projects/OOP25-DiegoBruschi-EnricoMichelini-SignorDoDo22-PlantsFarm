package it.unibo.plantsfarm.model.items.api;

import it.unibo.plantsfarm.model.plant.Rarity;

/**
 * Interface representing a generic farm item.
 */
public interface ItemsFarm {

    /**
     * Returns the type of this tool.
     *
     * The available tool types are:
     * {@code HOE}, {@code WATERCAN}, and {@code FERTILIZER}.
     *
     * @return the tool type
     */
    Tooltype getTooltype();

    /**
     * Upgrades the item if it has enough experience.
     * When the experience reaches the required value defined by the MaxExperienceforLevel
     * the item can be upgraded and its experience is reset.
     *
     */
    void upgrade();

    /**
     * Increases the experience of the item when it is used.
     */
    void useItem();

    /**
     * Returns the current level of the item (NOT THE EXPERIENCE THEY ARE NOT THE SAME).
     *
     * @return the current level
     */
    int getLevel();

    /**
     * Returns the current experience of the item.
     *
     * @return the current experience
     */
    int getExperience();

    /**
     * Returns the amount of experience required to reach the next level.
     *
     * @return the experience required for the next level
     */
    int getExperienceForLevel();

    /**
     * Returns the maximum level that this item can reach.
     *
     * @return the maximum level
     */
    int getMaxLevel();

    /**
     * Returns the minimum level of this item.
     *
     * @return the minimum level
     */
    int getMinLevel();

    /**
     * Update the Rarity of item based on the level.
     *
     * @param level
     */
    public void updateRarity(int level);

    /**
     * Return the rarity of the item.
     *
     * @return return current item's rarity
     */
    public Rarity getRarityItem();

    /**
     * Enumeration of all supported tool types.
     */
    enum Tooltype {
        HOE,
        WATERCAN,
        FERTILIZER
    }
}
