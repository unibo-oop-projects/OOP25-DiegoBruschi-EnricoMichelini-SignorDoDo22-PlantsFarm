package it.unibo.plantsfarm.view.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//import ch.qos.logback.classic.Logger;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

public final class TileManager {

    private static final int TILE_ARRAY_SIZE = 2000;

    private static final int ASSET_ORIGINAL_TILE_SIZE = 16;
    private static final int ASSET_SCALE = 3;
    private static final int ASSET_ACTUAL_SIZE = ASSET_ORIGINAL_TILE_SIZE * ASSET_SCALE;
    //private static final Logger LOGGER = Logger.getLogger(TileManager.class.getName());

    private final ImplViewGamePanel gp;
    private final Tile[] tile;
    private final int[][] mapTileNum;
    private int tileIndex;

    public TileManager(final ImplViewGamePanel gp) {
        this.gp = gp;

        this.tile = new Tile[TILE_ARRAY_SIZE];
        this.mapTileNum = new int[ImplViewGamePanel.MAX_WORLD_COL][ImplViewGamePanel.MAX_WORLD_ROW];

        setupTileImage();
        loadMap("/maps/map.txt");
    }

    public void setupTileImage() {
        setupTile(tileIndex, "grass.png");
        tileIndex++;
        setupTile(tileIndex, "pathVer.png");
        tileIndex++;
        setupTile(tileIndex, "dirtOrnamental.png");
        tileIndex++;
        setupTile(tileIndex, "wall.png");
        tileIndex++;
        setupTile(tileIndex, "tree.png");
        tileIndex++;
        setupTile(tileIndex, "spawn.png");
        tileIndex++;
        setupTile(tileIndex, "flowers.png");
        tileIndex++;
        setupTile(tileIndex, "floor.png");
        tileIndex++;
        setupTile(tileIndex, "lessFlowers.png");
        tileIndex++;
        setupTile(tileIndex, "pathOri.png");
        tileIndex++;
        setupTile(tileIndex, "path.png");
        tileIndex++;
        setupTile(tileIndex, "dirtContained.png");
        tileIndex = 20;

        // Shop slicing and loading
        final int numColonne = 9;
        final int numRighe = 5;

        final int shopSourceSliceSize = ASSET_ACTUAL_SIZE * 3;

        final BufferedImage bigSheet = new SpriteLoader("/icons/tiles/shop.png").getImage();

        for (int row = 0; row < numRighe; row++) {
            for (int col = 0; col < numColonne; col++) {
                tile[tileIndex] = new Tile();
                tile[tileIndex].image = bigSheet.getSubimage(
                    col * shopSourceSliceSize,
                    row * shopSourceSliceSize,
                    shopSourceSliceSize,
                    shopSourceSliceSize
                );
                tileIndex++;
            }
        }

        // Well slicing and loading
        final int numColonnePozzo = 3;
        final int numRighePozzo = 3;
        final int wellSourceSliceSize = ASSET_ACTUAL_SIZE;

        final BufferedImage bigSheetPozzo = new SpriteLoader("/icons/tiles/well.png").getImage();

        for (int row = 0; row < numRighePozzo; row++) {
            for (int col = 0; col < numColonnePozzo; col++) {
                tile[tileIndex] = new Tile();
                tile[tileIndex].image = bigSheetPozzo.getSubimage(
                    col * wellSourceSliceSize,
                    row * wellSourceSliceSize,
                    wellSourceSliceSize,
                    wellSourceSliceSize
                );
                tileIndex++;
            }
        }
    }

    private void setupTile(final int index, final String fileName) {
        tile[index] = new Tile();
        tile[index].image = new SpriteLoader("/icons/tiles/" + fileName).getImage();
    }

    public void loadMap(final String filePath) {
        try {
            final InputStream is = getClass().getResourceAsStream(filePath);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }

                final String[] numbers = line.split(" ");
                for (int column = 0; column < ImplViewGamePanel.MAX_WORLD_COL; column++) {
                    if (column < numbers.length) {
                        final int num = Integer.parseInt(numbers[column]);
                        mapTileNum[column][row] = num;
                    }
                }
            }
            br.close();
        } catch (final IOException | NumberFormatException e) {
            //LOGGER.error("Errore durante il caricamento della mappa", e);
            e.printStackTrace();
        }
    }

    public void drawTile(final Graphics2D g2, final int cameraX, final int cameraY) {
        for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
            for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {

                final int tileNum = mapTileNum[col][row];

                if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                    continue;
                }

                final int worldX = col * ImplViewGamePanel.TILE_SIZE;
                final int worldY = row * ImplViewGamePanel.TILE_SIZE;
                final int screenX = worldX - cameraX;
                final int screenY = worldY - cameraY;

                if (screenX + ImplViewGamePanel.TILE_SIZE > 0
                    && screenX < gp.getWidth()
                    && screenY + ImplViewGamePanel.TILE_SIZE > 0
                    && screenY < gp.getHeight()) {

                    g2.drawImage(tile[tileNum].image,
                        screenX,
                        screenY,
                        ImplViewGamePanel.TILE_SIZE,
                        ImplViewGamePanel.TILE_SIZE,
                        null
                    );
                }
            }
        }
    }
}
