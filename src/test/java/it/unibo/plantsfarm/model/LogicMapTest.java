package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.tiles.TileMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogicMapTest {
    
    @Test
    void testMapLoading() {
        TileMap map = new TileMap();
        map.loadMap("/maps/map.txt");

        assertTrue(map.getSoilList().size() == 64);
        assertTrue(map.getSolidBlocks().size() == 384);
    }

    @Test
    void testSolidBlocks() {
        TileMap map = new TileMap();
        map.loadMap("/maps/map.txt");

        assertTrue(map.getTileId(0, 0) == 4);
        assertTrue(map.isSolid(map.getTileId(0, 0)));
        assertTrue(map.getTileId(10, 10) == 0);
        assertTrue(!map.isSolid(map.getTileId(10, 10)));
    }

    @Test
    void testSoils() {
        TileMap map = new TileMap();
        map.loadMap("/maps/map.txt");
        
        assertTrue(!map.isSoil(map.getTileId(0, 0)));
        assertTrue(map.getTileId(4, 21) == 19);
        assertTrue(map.isSoil(map.getTileId(4, 21)));
        assertTrue(map.getTileId(6, 23) == 76);
        assertTrue(map.isSoil(map.getTileId(6, 23)));
    }
}
