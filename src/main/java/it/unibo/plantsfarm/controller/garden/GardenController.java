package it.unibo.plantsfarm.controller.garden;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;

import java.awt.Rectangle;
import java.util.List;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.garden.GardenModel;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.Soil;

/**
 * Controller for garden logic.
 */
public class GardenController {
    private GameState gameState;
    private AbstractPlayer player;
    private GardenModel gardenModel;

    /**
     * Creates a new GardenController.
     *
     * @param gameState The game state.
     * @param player    The player.
     */
    public GardenController (GameState gameState, AbstractPlayer player) {
        this.gameState = gameState;
        this.player = player;
        this.gardenModel = new GardenModel();
    }

    /**
     * Water the plant.
     *
     * @param now The current time in milliseconds.
     */
    public final void innaffia(final long now) {
        for (final Soil zolla : gardenModel.getSoils()) {
            if (zolla.getCoordinate().contains(player.getHitBox())) {
                if (zolla.getIsPlanted()) {
                    zolla.getPlant().water(now);
                    break;
                }
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
    public final void pianta(PlantType type) {
        if (type != null) {
            Plant pianta = new Plant(type);

            for (final Soil zolla : gardenModel.getSoils()) {
                if (zolla.getCoordinate().contains(player.getHitBox())) {
                    if (!zolla.getIsPlanted()) {
                        if ((zolla.getTileId() == 76 && !type.isEdible()) || (zolla.getTileId() != 76 && type.isEdible())) {
                            zolla.setPlanted(pianta);
                        }
                    } else {
                        gameState.addHarvest(zolla.getPlant().getType(), zolla.getPlant().harvest());
                    }
                }
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
    public Soil isPlayerOnSoil(final Rectangle hitbox) {
        for (Soil soilRect : gardenModel.getSoils()) {
            if(soilRect.getCoordinate().contains(hitbox)){
                return soilRect;
            }
        }
        return null;
    }

}