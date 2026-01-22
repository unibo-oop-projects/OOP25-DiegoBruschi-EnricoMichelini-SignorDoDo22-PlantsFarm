package it.unibo.plantsfarm.view.Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import it.unibo.plantsfarm.view.GamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

public class TileManager {
    
    private ImplViewGamePanel gp;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(ImplViewGamePanel gp) {
        this.gp = gp;

        tile = new Tile[2000];
        
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

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

        int numColonne = 9;
        int numRighe = 5;
        int tileSize = 16 * gp.SCALE * 3;
   
        int indexPartenza = 20;

        //NON POSSO ATTRAVERSARE 
        BufferedImage bigSheet = new SpriteLoader("/icons/tiles/shop.png").getImage();

        for (int row = 0; row < numRighe; row++) {
            for (int col = 0; col < numColonne; col++) {
      
                tile[indexPartenza] = new Tile();

                tile[indexPartenza].image = bigSheet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
      
                indexPartenza++;
            }
        }

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
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
            for (int row = 0; row < gp.maxScreenRow; row++) {
                String line = br.readLine();
                for (int column = 0; column < gp.maxScreenCol; column++) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = num;
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }

    public void drawTile(Graphics2D g2, int cameraX, int cameraY) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x - cameraX  , y - cameraY, gp.tileSize, gp.tileSize, null);
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
