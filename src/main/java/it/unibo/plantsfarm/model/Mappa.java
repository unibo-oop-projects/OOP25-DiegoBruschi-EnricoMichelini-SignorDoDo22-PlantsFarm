package it.unibo.plantsfarm.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public final class Mappa {

    private static final int TILE_SIZE = 48;
    private static final int MAX_WORLD_COL = 66;
    private static final int MAX_WORLD_ROW = 21;
    private int[][] logicMap1 = new int[MAX_WORLD_ROW][MAX_WORLD_COL];
    private List<Soil> pod = new LinkedList<>(List.of());

    public void loadMap(final String filePath) {

    try {
        final InputStream is = getClass().getResourceAsStream(filePath);
        final BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

        for (int row = 0; row < MAX_WORLD_ROW; row++) {
            final String line = br.readLine();
            final String[] numbers = line.split(" ");

            for (int col = 0; col < MAX_WORLD_COL; col++) {
                final int num = Integer.parseInt(numbers[col]);
                this.logicMap1[row][col] = num;
                final int worldX = col * TILE_SIZE; 
                final int worldY = row * TILE_SIZE;
                if (num == 2 || num >= 11 && num <= 19) {
                    final Rectangle rect = new Rectangle(worldX, worldY, TILE_SIZE, TILE_SIZE);
                    this.pod.add(new Soil(rect));
                }
            }
        }
        br.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public List<Soil> getPod() {
        return this.pod;
    }
}
