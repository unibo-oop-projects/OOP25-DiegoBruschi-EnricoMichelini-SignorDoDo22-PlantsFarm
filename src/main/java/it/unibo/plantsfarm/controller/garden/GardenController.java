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
import it.unibo.plantsfarm.model.tiles.TileMap;

public class GardenController {
    private GameState gameState;
    private AbstractPlayer player;
    private GardenModel gardenModel;

    private TileMap map = new TileMap();



    public GardenController (GameState gameState, AbstractPlayer player) {
        this.gameState = gameState;
        this.player = player;
        this.gardenModel = new GardenModel();
        this.map.loadMap("/maps/map.txt");
    }

    public final void innaffia(final long now) {
        final double posX = player.getPosx();
        final double posY = player.getPosy();
        final Rectangle hitbox = new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);
        for (final Soil zolla : map.soilList) {
            if (zolla.getCoordinate().contains(hitbox)) {
                if (zolla.getIsPlanted()) {
                    zolla.getPlant().water(now);
                    break;
                }
            }
        }
    }

    public final void updateSoil(final Long now) {
        for (final Soil zolla : map.soilList) {
            final Plant plant = zolla.getPlant();
            if (plant != null) {
                plant.updateNeedsWater(now);
                plant.increaseGrowthStage(now);
                System.out.println(plant.toString());
            }
        }
    }

    public final void pianta(PlantType plant) {
        final double posX = player.getPosx();
        final double posY = player.getPosy();
        if (plant != null) {
            Plant pianta = new Plant(plant);
            final Rectangle hitbox = new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);

            for (final Soil zolla : map.soilList) {
                if (zolla.getCoordinate().contains(hitbox) && player.getInventory().getItem(HOE).getLevel() == 0) {
                    if (!zolla.getIsPlanted()) {
                        zolla.setPlanted(pianta);
                    } else {
                        gameState.addHarvest(zolla.getPlant().getType(), zolla.getPlant().harvest());
                    }
                }

            }
        }
    }

    public final List<Soil> getSoilList() {
        return map.soilList;
    }

    public boolean isPlayerOnSoil(final Rectangle hitbox) {
        for (Soil soilRect : gardenModel.getSoils()) {
            if(hitbox.contains(soilRect.getCoordinate())){
                return true;
            }
        }
        return false;
    }
}