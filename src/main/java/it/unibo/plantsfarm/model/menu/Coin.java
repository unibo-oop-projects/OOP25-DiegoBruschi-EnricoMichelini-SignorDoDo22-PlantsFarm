package it.unibo.plantsfarm.model.menu;

/**
 * Represents the coins of the player.
 */
public final class Coin {

    private int value;

    /**
     * Creates a new Coin wallet with an initial value.
     *
     * @param initialValue The starting amount of coins.
     */
    public Coin(final int initialValue) {
        this.value = initialValue;
    }

    /**
     * Adds a specified amount to the balance.
     *
     * @param amount The amount to add.
     */
    public void addAmount(final int amount) {
        if (amount > 0) {
            this.value += amount;
        }
    }

    /**
     * Removes a specified amount from the balance.
     *
     * @param amount The amount to remove.
     * @return True if the transaction was successful, false otherwise.
     */
    public boolean removeAmount(final int amount) {
        if (amount > 0 && this.value >= amount) {
            this.value -= amount;
            return true;
        }
        return false;
    }

    /**
     * Gets the current amount of coins.
     *
     * @return The current balance.
     */
    public int getValue() {
        return this.value;
    }
}
