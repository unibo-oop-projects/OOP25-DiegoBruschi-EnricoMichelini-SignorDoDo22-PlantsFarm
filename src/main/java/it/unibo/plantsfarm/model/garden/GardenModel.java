package it.unibo.plantsfarm.model.garden;

import java.util.LinkedList;
import java.util.List;
import java.awt.Rectangle;

import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

/**
 * Manages the logic of the garden.
 */
public class GardenModel {

    private List<Soil> soils = new LinkedList<>();
    private final List<PlantArea> areas = new LinkedList<>();
    private final TileMap map = new TileMap();

    /**
     * Initializes the garden model.
     */
    public GardenModel() {
        this.map.loadMap("/maps/map.txt");
        this.soils = this.map.getSoilList();
        createAreas();
    }

    /**
     * Identify area centers and creates 5x5 PlantAreas.
     * The center of each area is tile 76.
     */
    private void createAreas() {
        final int tileSize = ImplViewGamePanel.TILE_SIZE;

        for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
            for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {

                if (map.getTileId(row, col) == 76 && col < 40) { //X DIEGO < 40 è per non creare aree nella parte della "serra"

                    final int startX = (col - 2) * tileSize;
                    final int startY = (row - 2) * tileSize;
                    final int size = tileSize * 5;

                    areas.add(new PlantArea(new Rectangle(startX, startY, size, size)));
                }
            }
        }
        for (final Soil soil : soils) {
            for (final PlantArea area : areas) {
                if (area.contains(soil)) {
                    area.addSoil(soil);
                    break;
                }
            }
        }
    }

    /**
     * Updates the state of the garden every frame.
     * Update each PlantArea.
     *
     * @param now The current time in milliseconds.
     */
    public void updateGame(final long now) {
        for (final PlantArea area : areas) {
            area.updateArea(now);
        }
    }

    /**
     * Give the list of all soil tiles in the garden.
     *
     * @return The list of Soil objects.
     */
    public final List<Soil> getSoils() {
        return this.soils;
    }

}