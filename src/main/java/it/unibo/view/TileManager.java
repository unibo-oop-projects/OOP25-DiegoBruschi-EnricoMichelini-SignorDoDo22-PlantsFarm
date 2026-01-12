package it.unibo.view;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import it.unibo.view.GamePanel;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[2000];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/smallMap.txt");;
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("resources/icons/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("resources/icons/tiles/pathVer.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirt.png"));
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/icons/tiles/wall.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/icons/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/icons/tiles/spawn.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\chest.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\floor.png"));

            try {
                int numColonne = 9;
                int numRighe = 5;
                int tileSize = 16 * gp.scale * 3;
    
                int indexPartenza = 20;

                java.awt.image.BufferedImage bigSheet = ImageIO.read(getClass().getResourceAsStream("/icons/tiles/shop.png"));

                for (int row = 0; row < numRighe; row++) {
                    for (int col = 0; col < numColonne; col++) {
            
                        tile[indexPartenza] = new Tile();

                        tile[indexPartenza].image = bigSheet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
            
                        indexPartenza++;
                    }
                }           

            } catch (IOException e) {
                e.printStackTrace();
            }

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/icons/tiles/pathOri.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/icons/tiles/path.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtUp.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtDown.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtLeft.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtRight.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtLUpRight.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtLDownRight.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtLDownLeft.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtLUpLeft.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("src\\main\\resources\\icons\\tiles\\dirtContained.png"));


            
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
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

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
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
