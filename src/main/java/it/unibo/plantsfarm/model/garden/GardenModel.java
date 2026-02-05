package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
//import java.util.LinkedList;
//import java.util.List;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.TileMap;
//import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public class GardenModel {

    private double posX = ImplViewGamePanel.WORLD_WIDTH / 2;
    private double posY = ImplViewGamePanel.WORLD_HEIGHT / 2;

    boolean overSoil;

    //private List<Soil> soils = new LinkedList<>(List.of());
    private TileMap map = new TileMap();

    //private GameState gameState;
    //private ImplControllerGamePanel controllerGamePanel;
    //private AbstractPlayer player;
    //private GardenModel gardenModel;

    public GardenModel () {
        this.map.loadMap("/maps/map.txt");
        //this.soils = this.map.getSoilList();
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

    public final boolean isOverSoil(PlantType plant) {
        if (plant != null){
            //Plant pianta = new Plant(plant);
            final Rectangle hitbox = new Rectangle((int) posX + 26, (int) posY + 26, 16, 16);
            for (final Soil zolla : map.soilList) {
                if (zolla.getCoordinate().contains(hitbox)) {
                    overSoil = true;
                    break;
                }
            }
        }
        return overSoil;
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
}
