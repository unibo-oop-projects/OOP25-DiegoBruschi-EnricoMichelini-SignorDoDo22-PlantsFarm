package it.unibo.plantsfarm.controller.garden;

import java.awt.Rectangle;
import java.util.List;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.garden.GardenModel;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.tiles.Soil;

/**
 * Controller for garden logic.
 */
public class GardenController {
    private static final int ORNAMENTAL_SOIL = 2;

    private GameState gameState;
    private Player player;
    private GardenModel gardenModel;

    /**
     * Creates a new GardenController.
     *
     * @param gameState The game state.
     * @param player    The player.
     */
    public GardenController(final GameState gameState, final Player player) {
        setGameState(gameState);
        setPlayer(player);
        setGardenModel();
    }

    /**
     * Water the plant.
     *
     * @param now The current time in milliseconds.
     */
    public final void innaffia(final long now) {
        for (final Soil zolla : gardenModel.getSoils()) {
            if (zolla.getCoordinate().contains(player.getHitBox()) && zolla.isPlanted()) {
                zolla.getPlant().water(now);
                break;
            }
        }
    }

    /**
     * Update the garden model.
     *
     * @param now The current time in milliseconds.
     */
    public final void updateSoil(final Long now) {
        this.gardenModel.updateGame(now);
    }

    /**
     * Plant or harvest.
     *
     * @param type The plant type selected.
     */
    public final void pianta(final PlantType type) {
        if (type != null) {
            final Plant pianta = new Plant(type);

            for (final Soil zolla : gardenModel.getSoils()) {
                if (zolla.getCoordinate().contains(player.getHitBox()) && !zolla.isPlanted()
                    && zolla.getTileId() == ORNAMENTAL_SOIL && !type.isEdible()
                    || zolla.getTileId() != ORNAMENTAL_SOIL && type.isEdible()
                ) {
                    zolla.setPlanted(pianta);
                }
            }
        }
    }

    /**
     * Harvest the plant if it's mature.
     */
    public final void harvest() {
        for (final Soil zolla : gardenModel.getSoils()) {
            if (zolla.getCoordinate().contains(player.getHitBox()) && zolla.isPlanted() && zolla.getPlant().isMature()) {
                gameState.addHarvest(zolla.getPlant().getType(), zolla.getPlant().harvest());
                break;
            }
        }
    }

    /**
     * Give the list of all soil tiles in the garden.
     *
     * @return The list of soil objects.
     */
    public final List<Soil> getSoilList() {
        return gardenModel.getSoils();
    }

    /**
     * Checks if the player is on a soil tile.
     *
     * @param hitbox The hitbox of the player.
     *
     * @return True if the player is on soil, false otherwise.
     */
    public Soil whichSoilIsPlayerOn(final Rectangle hitbox) {
        for (final Soil soilRect : gardenModel.getSoils()) {
            if (hitbox.intersects(soilRect.getCoordinate()) || soilRect.getCoordinate().contains(hitbox)) {
                return soilRect;
            }
        }
        return null;
    }

    private void setGameState(final GameState givenGameState) {
        this.gameState = givenGameState;
    }

    private void setPlayer(final Player givenPlayer) {
        this.player = givenPlayer;
    }

    private void setGardenModel() {
        this.gardenModel = new GardenModel();
    }
}
