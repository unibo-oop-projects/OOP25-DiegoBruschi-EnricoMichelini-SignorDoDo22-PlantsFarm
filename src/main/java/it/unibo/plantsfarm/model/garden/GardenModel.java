package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;
//import java.util.LinkedList;
//import java.util.List;
import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.TileMap;
//import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public class GardenModel {

    private List<Soil> soils = new LinkedList<>(List.of());
    private TileMap map = new TileMap();
    boolean overSoil;

    public GardenModel () {
        this.soils = this.map.getSoilList();
    }

    public final List<Soil> getSoils() {
        return this.soils;
    }
}
