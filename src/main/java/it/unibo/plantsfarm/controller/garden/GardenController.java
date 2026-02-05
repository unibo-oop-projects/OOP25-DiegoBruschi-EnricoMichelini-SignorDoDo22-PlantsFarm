package it.unibo.plantsfarm.controller.garden;

import java.awt.Rectangle;
import java.util.List;

//import com.fasterxml.jackson.databind.cfg.ContextAttributes.Impl;

import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.garden.GardenModel;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public class GardenController {
    private GameState gameState;
    private AbstractPlayer player;
    private GardenModel gardenModel;
    private double posX = ImplViewGamePanel.WORLD_WIDTH / 2;
    private double posY = ImplViewGamePanel.WORLD_HEIGHT / 2;
    private PlantType plant = ImplViewGamePanel.selectedPlant; 
    
    private TileMap map = new TileMap();



    public GardenController (GameState gameState, AbstractPlayer player) {
        this.gameState = gameState;
        this.player = player;
        this.gardenModel = new GardenModel();
        this.map.loadMap("/maps/map.txt");
    }

    public final void innaffia(final long now) {
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
                //System.out.println(plant.toString());
            }
        }
    }

    public final void pianta(PlantType plant) {
        if (plant != null){
            Plant pianta = new Plant(plant);
            final Rectangle hitbox = new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);
            for (final Soil zolla : map.soilList) {
                if (zolla.getCoordinate().contains(hitbox)) {
                    if (!zolla.getIsPlanted()) {
                        zolla.setPlanted(pianta);
                        //System.out.println(zolla.getPlant());
                        //System.out.println("PLANT TYPE" + zolla.getPlant().currentStageTime);
                    } else {
                        zolla.getPlant().harvest();
                    }
                }
            }
        }
    }

    public final List<Soil> getSoilCoordinate() {
        return gardenModel.getSoils();
    }
}
