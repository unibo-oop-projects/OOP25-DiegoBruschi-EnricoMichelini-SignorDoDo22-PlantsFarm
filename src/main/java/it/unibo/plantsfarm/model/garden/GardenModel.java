package it.unibo.plantsfarm.model.garden;

import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.TileMap;

public class GardenModel {

    private List<Soil> soils = new LinkedList<>(List.of());
    private TileMap map = new TileMap();

    public GardenModel () {
        this.map.loadMap("/maps/map.txt");
        this.soils = this.map.getSoilList();
    }

    public final List<Soil> getSoils() {
        return this.soils;
    }
}
