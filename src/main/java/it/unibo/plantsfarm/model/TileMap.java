package it.unibo.plantsfarm.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

// Importiamo le costanti dalla View per essere sicuri che Logica e Grafica coincidano
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public final class TileMap {

    // NON definiamo più le costanti qui, le prendiamo dalla View (o da una classe Config condivisa)
    // per garantire che Model e View siano sincronizzati.
    private final int[][] logicMap1; 
    
    // Liste per la logica di gioco
    public List<Soil> soilList = new LinkedList<>();
    public List<SolidBlock> solidBlocks = new LinkedList<>();

    public TileMap() {
        this.logicMap1 = new int[ImplViewGamePanel.MAX_WORLD_ROW][ImplViewGamePanel.MAX_WORLD_COL];
    }

    public void loadMap(final String filePath) {

        this.soilList.clear();
        this.solidBlocks.clear();

        try {
            final InputStream is = getClass().getResourceAsStream(filePath);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < ImplViewGamePanel.MAX_WORLD_ROW; row++) {
                final String line = br.readLine();
                if (line == null) break;

                final String[] numbers = line.split(" ");

                for (int col = 0; col < ImplViewGamePanel.MAX_WORLD_COL; col++) {
                    
                    // Controllo di sicurezza per evitare crash
                    if (col >= numbers.length) break;

                    final int num = Integer.parseInt(numbers[col]);
                    this.logicMap1[row][col] = num;

                    // CALCOLO COORDINATE DINAMICO
                    // Usiamo ImplViewGamePanel.TILE_SIZE invece di 48 fisso
                    final int worldX = col * ImplViewGamePanel.TILE_SIZE;
                    final int worldY = row * ImplViewGamePanel.TILE_SIZE;
                    final int size = ImplViewGamePanel.TILE_SIZE;

                    // CREAZIONE SOIL (Terreno coltivabile)
                    // (2 = terra zappata, 11-19 = variazioni terra)
                    if (num == 2 || (num >= 11 && num <= 19)) {
                        final Rectangle rect = new Rectangle(worldX, worldY, size, size);
                        this.soilList.add(new Soil(rect));
                    }

                    // CREAZIONE SOLID BLOCKS (Collisioni: Muri, Alberi, Acqua, etc.)
                    if (isSolid(num)) {
                        final Rectangle rect = new Rectangle(worldX, worldY, size, size);
                        this.solidBlocks.add(new SolidBlock(rect));
                    }
                }
            }
            br.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to determine if a tile ID is solid.
     * Rende il codice più leggibile rispetto all'if gigante.
     */
    private boolean isSolid(int num) {
        return num == 3 
            || num == 4 
            || num == 6 
            || (num >= 22 && num <= 26) // Muri shop
            || (num >= 31 && num <= 35) // Muri shop
            || (num >= 40 && num <= 45) // Muri shop
            || (num >= 48 && num <= 54) // Muri shop/tetti
            || (num >= 58 && num <= 60) // Tetti
            || num == 66 
            || (num >= 68 && num <= 70) // Pozzo
            || num == 72;
    }

    public List<Soil> getSoilList() {
        return this.soilList;
    }
    
    public List<SolidBlock> getSolidBlocks() {
        return this.solidBlocks;
    }
}
