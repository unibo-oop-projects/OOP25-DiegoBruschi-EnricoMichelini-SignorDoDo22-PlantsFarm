package it.unibo.plantsfarm.model.plant;

/**
 * Encapsulates the effect data for a ornamental plant.
 */
public final class EffectInfo {

    private final PlantEffect type;
    private final double value;

    /**
     * Constructor for EffectInfo.
     *
     * @param type   The type of the effect.
     * @param value  The value of the effect.
     */
    public EffectInfo(final PlantEffect type, final double value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the type of the effect.
     *
     * @return The PlantEffect type.
     */
    public PlantEffect getType() {
        return type;
    }

    /**
     * Gets the value of the effect.
     *
     * @return The value.
     */
    public double getValue() {
        return value;
    }

}
