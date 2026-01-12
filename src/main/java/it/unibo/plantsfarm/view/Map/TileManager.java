package it.unibo.plantsfarm.view.Map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[2000]; 
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/smallMap.txt");
    }

    public void getTileImage() {
        setup(0, "/tiles/grass.png");
        setup(1, "/tiles/pathVer.png");
        setup(2, "/tiles/dirt.png");      
        setup(3, "/tiles/wall.png");
        setup(4, "/tiles/tree.png");
        setup(5, "/tiles/spawn.png");
        setup(6, "/tiles/chest.png");
        setup(7, "/tiles/floor.png");
        setup(9, "/tiles/pathOri.png");
        setup(10, "/tiles/path.png");
        setup(11, "/tiles/dirtUp.png");
        setup(12, "/tiles/dirtDown.png");
        setup(13, "/tiles/dirtLeft.png");
        setup(14, "/tiles/dirtRight.png");
        setup(15, "/tiles/dirtLUpRight.png");
        setup(16, "/tiles/dirtLDownRight.png");
        setup(17, "/tiles/dirtLDownLeft.png");
        setup(18, "/tiles/dirtLUpLeft.png");
        setup(19, "/tiles/dirtContained.png");
        loadShopTiles();
    }

    public void setup(int index, String imagePath) {
        try {
            tile[index] = new Tile();
            InputStream is = getClass().getResourceAsStream(imagePath);
            
            if (is == null) {
                System.err.println("Image not found: " + imagePath);
                tile[index].image = new java.awt.image.BufferedImage(48, 48, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                return; 
            }
            
            tile[index].image = ImageIO.read(is);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadShopTiles() {
        try {
            int tileSize = gp.tileSize*3; 
            int numColonne = 9;
            int numRighe = 5;
            int indexPartenza = 20;

            InputStream is = getClass().getResourceAsStream("/tiles/shop.png");
            if (is == null) {
                System.err.println("Shop tileset not found!");
                return;
            }
            
            java.awt.image.BufferedImage bigSheet = ImageIO.read(is);

            for (int row = 0; row < numRighe; row++) {
                for (int col = 0; col < numColonne; col++) {
                    if (indexPartenza < tile.length) {
                        tile[indexPartenza] = new Tile();
                        if ((col * tileSize + tileSize) <= bigSheet.getWidth() && (row * tileSize + tileSize) <= bigSheet.getHeight()) {
                             tile[indexPartenza].image = bigSheet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
                        }
                        indexPartenza++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                System.err.println("Map file not found: " + filePath);
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                if (line == null) break;

                String numbers[] = line.split(" ");

                while (col < gp.maxScreenCol) {
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    }
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            if (tile[tileNum] != null && tile[tileNum].image != null) {
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            }
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}