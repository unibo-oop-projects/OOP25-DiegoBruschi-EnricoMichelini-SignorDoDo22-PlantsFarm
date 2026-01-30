package it.unibo.plantsfarm.view.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

public final class TileManager {
    private static final int TILE_ARRAY_SIZE = 2000;
    private final ImplViewGamePanel gp;
    private final Tile[] tile;
    private final int[][] mapTileNum;

    public TileManager(final ImplViewGamePanel gp) {
        this.gp = gp;

        tile = new Tile[TILE_ARRAY_SIZE];
        mapTileNum = new int[ImplViewGamePanel.MAXSCREENCOL][ImplViewGamePanel.MAXSCREENROW];

        getTileImage();
        loadMap("/maps/map.txt");
    }

    public void getTileImage() {

        tile[0] = new Tile();
        tile[0].image = new SpriteLoader("/icons/tiles/grass.png").getImage();

        tile[1] = new Tile();
        tile[1].image = new SpriteLoader("/icons/tiles/pathVer.png").getImage();

        tile[2] = new Tile();
        tile[2].image = new SpriteLoader("/icons/tiles/dirt.png").getImage();

        //NON POSSO ATTRAVERSARE 
        tile[3] = new Tile();
        tile[3].image = new SpriteLoader("/icons/tiles/wall.png").getImage();

        //NON POSSO ATTRAVERSARE 
        tile[4] = new Tile();
        tile[4].image = new SpriteLoader("/icons/tiles/tree.png").getImage();

        tile[5] = new Tile();
        tile[5].image = new SpriteLoader("/icons/tiles/spawn.png").getImage();

        //NON POSSO ATTRAVERSARE 
        tile[6] = new Tile();
        tile[6].image = new SpriteLoader("/icons/tiles/chest.png").getImage();

        tile[7] = new Tile();
        tile[7].image = new SpriteLoader("/icons/tiles/floor.png").getImage();

        tile[9] = new Tile();
        tile[9].image = new SpriteLoader("/icons/tiles/pathOri.png").getImage();

        tile[10] = new Tile();
        tile[10].image = new SpriteLoader("/icons/tiles/path.png").getImage();

        tile[11] = new Tile();
        tile[11].image = new SpriteLoader("/icons/tiles/dirtUp.png").getImage();

        tile[12] = new Tile();
        tile[12].image = new SpriteLoader("/icons/tiles/dirtDown.png").getImage();

        tile[13] = new Tile();
        tile[13].image = new SpriteLoader("/icons/tiles/dirtLeft.png").getImage();

        tile[14] = new Tile();
        tile[14].image = new SpriteLoader("/icons/tiles/dirtRight.png").getImage();

        tile[15] = new Tile();
        tile[15].image = new SpriteLoader("/icons/tiles/dirtLUpRight.png").getImage();

        tile[16] = new Tile();
        tile[16].image = new SpriteLoader("/icons/tiles/dirtLDownRight.png").getImage();

        tile[17] = new Tile();
        tile[17].image = new SpriteLoader("/icons/tiles/dirtLDownLeft.png").getImage();

        tile[18] = new Tile();
        tile[18].image = new SpriteLoader("/icons/tiles/dirtLUpLeft.png").getImage();

        tile[19] = new Tile();
        tile[19].image = new SpriteLoader("/icons/tiles/dirtContained.png").getImage();

        //Shop cutting and building tiles (NON POSSO ATTRAVERSARE)
        final int numColonne = 9;
        final int numRighe = 5;
        final int tileSize = 16 * ImplViewGamePanel.SCALE * 3;
        int indexPartenza = 20;

        final BufferedImage bigSheet = new SpriteLoader("/icons/tiles/shop.png").getImage();

        for (int row = 0; row < numRighe; row++) {
            for (int col = 0; col < numColonne; col++) {

                tile[indexPartenza] = new Tile();

                tile[indexPartenza].image = bigSheet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
                indexPartenza++;
            }
        }
    }

    public void loadMap(final String filePath) {
        try {
            final InputStream is = getClass().getResourceAsStream(filePath);
            final BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
            for (int row = 0; row < ImplViewGamePanel.MAXSCREENROW; row++) {
                final String line = br.readLine();
                for (int column = 0; column < ImplViewGamePanel.MAXSCREENCOL; column++) {
                    final String[] numbers = line.split(" ");

                    final int num = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = num;
                }
            }
            br.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

   public void drawTile(final Graphics2D g2, final int cameraX, final int cameraY) {
    for (int row = 0; row < ImplViewGamePanel.MAXSCREENROW; row++) {
        for (int col = 0; col < ImplViewGamePanel.MAXSCREENCOL; col++) {

            final int tileNum = mapTileNum[col][row];
            final int worldX = col * ImplViewGamePanel.tileSize;
            final int worldY = row * ImplViewGamePanel.tileSize;
            final int screenX = worldX - cameraX;
            final int screenY = worldY - cameraY;

            if (screenX + ImplViewGamePanel.tileSize > 0
                && screenX < ImplViewGamePanel.worldWidth
                && screenY + ImplViewGamePanel.tileSize > 0
                && screenY < gp.getHeight()) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, ImplViewGamePanel.tileSize, ImplViewGamePanel.tileSize, null);
            }
        }
    }
    }
}
