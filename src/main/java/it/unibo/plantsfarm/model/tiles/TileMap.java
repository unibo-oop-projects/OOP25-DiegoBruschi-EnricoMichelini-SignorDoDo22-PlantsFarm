package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.plantsfarm.model.garden.SoilSaving;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.map.TileManager;

public final class TileMap {

    private static final int ORNAMENTAL_SOIL = 2;
    private static final int NORMAL_SOIL = 11;

    private static final int WALL = 3;
    private static final int TREE = 4;

    private static final int BEGIN_SHOP_FIRST_ROW = 22;
    private static final int END_SHOP_FIRST_ROW = 26;
    private static final int BEGIN_SHOP_SECOND_ROW = 31;
    private static final int END_SHOP_SECOND_ROW = 35;
    private static final int BEGIN_SHOP_THIRD_ROW = 40;
    private static final int END_SHOP_THIRD_ROW = 45;
    private static final int BEGIN_SHOP_FOURTH_ROW = 48;
    private static final int END_SHOP_FOURTH_ROW = 54;
    private static final int BEGIN_SHOP_FIFTH_ROW = 58;
    private static final int END_SHOP_FIFTH_ROW = 60;

    private static final int WELL_FIRST_ROW = 66;
    private static final int BEGIN_WELL_SECOND_ROW = 68;
    private static final int END_WELL_SECOND_ROW = 70;
    private static final int WELL_THIRD_ROW = 72;

    private static final Logger LOGGER = Logger.getLogger(TileManager.class.getName());

    private final List<Soil> soilList = new LinkedList<>();
    private final List<SolidBlock> solidBlocks = new LinkedList<>();

    private final int[][] logicMap1;
    private final SoilSaving saveController = new SoilSaving();

    public TileMap() {
        this.logicMap1 = new int[ImplViewGamePanel.MAX_WORLD_ROW][ImplViewGamePanel.MAX_WORLD_COL];
    }

    public void loadMap(final String filePath) {
        this.soilList.clear();
        this.solidBlocks.clear();

        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }

                final String[] numbers = line.split(" ");

                for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {
                    if (col >= numbers.length) {
                        break;
                    }

                    final int num = Integer.parseInt(numbers[col]);
                    this.logicMap1[row][col] = num;
                    final int worldX = col * ImplViewGamePanel.TILE_SIZE;
                    final int worldY = row * ImplViewGamePanel.TILE_SIZE;
                    final int size = ImplViewGamePanel.TILE_SIZE;

                    if (isSoil(num)) {
                        this.soilList.add(new Soil(new Rectangle(worldX, worldY, size, size), num));
                    }

                    if (isSolid(num)) {
                        this.solidBlocks.add(new SolidBlock(new Rectangle(worldX, worldY, size, size)));
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il caricamento della mappa", e);
        }

        applySavedData();
    }

    private void applySavedData() {
        final List<Soil> savedProgress = saveController.loadGame();
        if (savedProgress == null) {
            return;
        }

        for (final Soil saved : savedProgress) {
            for (int i = 0; i < soilList.size(); i++) {
                final Soil current = soilList.get(i);
                if (current.getCoordinate().x == saved.getCoordinate().x
                    && current.getCoordinate().y == saved.getCoordinate().y
                ) {
                    this.soilList.set(i, saved);
                    break;
                }
            }
        }
    }

    public boolean isSoil(final int num) {
        return num == ORNAMENTAL_SOIL || num == NORMAL_SOIL;
    }

    public boolean isSolid(final int num) {
        return num == WALL || num == TREE
            || num >= BEGIN_SHOP_FIRST_ROW && num <= END_SHOP_FIRST_ROW 
            || num >= BEGIN_SHOP_SECOND_ROW && num <= END_SHOP_SECOND_ROW
            || num >= BEGIN_SHOP_THIRD_ROW && num <= END_SHOP_THIRD_ROW
            || num >= BEGIN_SHOP_FOURTH_ROW && num <= END_SHOP_FOURTH_ROW
            || num >= BEGIN_SHOP_FIFTH_ROW && num <= END_SHOP_FIFTH_ROW
            || num == WELL_FIRST_ROW
            || num >= BEGIN_WELL_SECOND_ROW && num <= END_WELL_SECOND_ROW
            || num == WELL_THIRD_ROW;
    }

    public List<Soil> getSoilList() {
        return this.soilList;
    }

    public List<SolidBlock> getSolidBlocks() {
        return this.solidBlocks;
    }

    //TO DO: ornamental
    public int getTileId(final int row, final int col) {
        if (row >= 0 && row < ImplViewGamePanel.MAX_WORLD_ROW && col >= 0 && col < ImplViewGamePanel.MAX_WORLD_COL) {
            return logicMap1[row][col];
        }
        return 0;
    }
}
