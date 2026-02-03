package it.unibo.plantsfarm.view.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

public final class TileManager {
    
    private static final int TILE_ARRAY_SIZE = 2000;
    
    // Costanti per il caricamento degli Asset (Sprite Sheet)
    // Queste dipendono dalla risoluzione originale dei tuoi file PNG, non dallo schermo.
    // Assumo che i tuoi asset siano stati creati con lo scale 3 originale.
    private static final int ASSET_ORIGINAL_TILE_SIZE = 16;
    private static final int ASSET_SCALE = 3; 
    private static final int ASSET_ACTUAL_SIZE = ASSET_ORIGINAL_TILE_SIZE * ASSET_SCALE; // 48px

    private final ImplViewGamePanel gp;
    private final Tile[] tile;
    private final int[][] mapTileNum;

    public TileManager(final ImplViewGamePanel gp) {
        this.gp = gp;

        this.tile = new Tile[TILE_ARRAY_SIZE];
        // Usiamo le costanti aggiornate della View (MAX_WORLD_...)
        this.mapTileNum = new int[ImplViewGamePanel.MAX_WORLD_COL][ImplViewGamePanel.MAX_WORLD_ROW];

        getTileImage();
        loadMap("/maps/map.txt");
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public void getTileImage() {

        // --- CARICAMENTO STANDARD ---
        setupTile(0, "grass.png");
        setupTile(1, "pathVer.png");
        setupTile(2, "dirt.png");
        setupTile(3, "wall.png"); // Collision
        setupTile(4, "tree.png"); // Collision
        setupTile(5, "spawn.png");
        setupTile(6, "chest.png"); // Collision
        setupTile(7, "floor.png");
        setupTile(9, "pathOri.png");
        setupTile(10, "path.png");
        
        // Dirt edges
        setupTile(11, "dirtUp.png");
        setupTile(12, "dirtDown.png");
        setupTile(13, "dirtLeft.png");
        setupTile(14, "dirtRight.png");
        setupTile(15, "dirtLUpRight.png");
        setupTile(16, "dirtLDownRight.png");
        setupTile(17, "dirtLDownLeft.png");
        setupTile(18, "dirtLUpLeft.png");
        setupTile(19, "dirtContained.png");

        // --- CARICAMENTO SHOP (Slicing) ---
        final int numColonne = 9;
        final int numRighe = 5;
        // Nel tuo codice originale usavi (16 * 3 * 3) = 144px per il taglio dello shop.
        // Mantengo questa logica per il taglio sorgente.
        final int shopSourceSliceSize = ASSET_ACTUAL_SIZE * 3; 
        
        int indexPartenza = 20;
        final BufferedImage bigSheet = new SpriteLoader("/icons/tiles/shop.png").getImage();

        for (int row = 0; row < numRighe; row++) {
            for (int col = 0; col < numColonne; col++) {
                tile[indexPartenza] = new Tile();
                // Tagliamo l'immagine originale
                tile[indexPartenza].image = bigSheet.getSubimage(
                    col * shopSourceSliceSize, 
                    row * shopSourceSliceSize, 
                    shopSourceSliceSize, 
                    shopSourceSliceSize
                );
                indexPartenza++;
            }
        }

        // --- CARICAMENTO POZZO (Slicing) ---
        final int numColonnePozzo = 3;
        final int numRighePozzo = 3;
        // Nel tuo codice originale usavi (16 * 3) = 48px per il pozzo
        final int wellSourceSliceSize = ASSET_ACTUAL_SIZE;
        
        int indexPartenzaPozzo = 65;
        final BufferedImage bigSheetPozzo = new SpriteLoader("/icons/tiles/well.png").getImage();

        for (int row = 0; row < numRighePozzo; row++) {
            for (int col = 0; col < numColonnePozzo; col++) {
                tile[indexPartenzaPozzo] = new Tile();
                tile[indexPartenzaPozzo].image = bigSheetPozzo.getSubimage(
                    col * wellSourceSliceSize, 
                    row * wellSourceSliceSize, 
                    wellSourceSliceSize, 
                    wellSourceSliceSize
                );
                indexPartenzaPozzo++;
            }
        }
    }
    
    private void setupTile(int index, String fileName) {
        tile[index] = new Tile();
        tile[index].image = new SpriteLoader("/icons/tiles/" + fileName).getImage();
    }

    public void loadMap(final String filePath) {
        try {
            final InputStream is = getClass().getResourceAsStream(filePath);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Aggiornato per usare MAX_WORLD_ROW e MAX_WORLD_COL
            for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
                final String line = br.readLine();
                if (line == null) break;
                
                final String[] numbers = line.split(" ");
                for (int column = 0; column < ImplViewGamePanel.MAX_WORLD_COL; column++) {
                    // Controllo di sicurezza per evitare crash se la mappa txt è più corta
                    if (column < numbers.length) {
                        final int num = Integer.parseInt(numbers[column]);
                        mapTileNum[column][row] = num;
                    }
                }
            }
            br.close();
        } catch (final IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void drawTile(final Graphics2D g2, final int cameraX, final int cameraY) {
        // Usiamo le costanti aggiornate
        for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
            for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {

                final int tileNum = mapTileNum[col][row];
                
                // Se la mappa ha un numero invalido, saltiamo (evita NullPointerException)
                if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                    continue;
                }

                final int worldX = col * ImplViewGamePanel.TILE_SIZE;
                final int worldY = row * ImplViewGamePanel.TILE_SIZE;
                final int screenX = worldX - cameraX;
                final int screenY = worldY - cameraY;

                // OTTIMIZZAZIONE RENDERING (Culling)
                // Disegna solo se il tile è dentro l'area visibile dello schermo (+ un margine di sicurezza)
                // Nota: uso gp.getWidth() e gp.getHeight() per sapere la dimensione reale della finestra
                if (screenX + ImplViewGamePanel.TILE_SIZE > 0
                    && screenX < gp.getWidth()
                    && screenY + ImplViewGamePanel.TILE_SIZE > 0
                    && screenY < gp.getHeight()) {

                    // Disegna l'immagine scalandola alla dimensione del TILE corrente (dinamico)
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