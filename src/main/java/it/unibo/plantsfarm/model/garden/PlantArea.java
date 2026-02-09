package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.plant.EffectInfo;
import it.unibo.plantsfarm.model.plant.PlantEffect;

/**
 * Represents a specific area in the garden containing multiple soils.
 * Calculate and apply passive effects based on the plant located at its geometric center.
 * The "centerSoil" represents the ornamental plant that provides bonuses to the growth of surrounding edible plants.
 */
public final class PlantArea {

    private final Rectangle bounds;
    private final List<Soil> soils = new ArrayList<>();
    private Soil centerSoil;

    /**
     * Creates a new PlantArea.
     *
     * @param bounds The rectangle defining the area limits.
     */
    public PlantArea(final Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Adds a soil to this area if it is contained.
     * Find out if the soil is the center of the area.
     *
     * @param soil The soil object to try to add.
     */
    public void addSoil(final Soil soil) {
        if (bounds.contains(soil.getCoordinate())) {
            soils.add(soil);
            if (soil.getCoordinate().contains(bounds.getCenterX(), bounds.getCenterY())) {
                this.centerSoil = soil;
            }
        }
    }

    /**
     * Updates all plants in this area, applying bonuses if applicable.
     *
     * @param now Current time in milliseconds.
     */
    public void updateArea(final long now) {
        double growthMultiplier = 1.0;
        double harvestMultiplier = 1.0;
        if (centerSoil != null && centerSoil.getIsPlanted()) {
            final Plant centerPlant = centerSoil.getPlant();
            final EffectInfo effect = centerPlant.getType().getEffectInfo();
            centerPlant.increaseGrowthStage(now, 1.0);
            centerPlant.updateNeedsWater(now);

            if (effect != null && centerPlant.isMature()) {
                if (effect.getType() == PlantEffect.GROWTH_SPEED) {
                    growthMultiplier = effect.getValue();
                } else if (effect.getType() == PlantEffect.BIG_HARVEST) {
                    harvestMultiplier = effect.getValue();
                }
            }
        }

        for (final Soil soil : soils) {
            if (soil == centerSoil) {
                continue;
            }

            if (soil.getIsPlanted()) {
                final Plant p = soil.getPlant();
                p.increaseGrowthStage(now, growthMultiplier);
                p.setHarvestMultiplier(harvestMultiplier);
                p.updateNeedsWater(now);
            }
        }
    }

    /**
     * Check if a soil belongs to this area.
     *
     * @param soil The soil to check.
     *
     * @return True if the soil is inside the area bounds, false otherwise.
     */
    public boolean contains(final Soil soil) {
        return bounds.contains(soil.getCoordinate());
    }
}
