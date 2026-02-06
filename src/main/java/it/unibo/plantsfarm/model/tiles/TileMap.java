package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public final class TileMap {

    private final int[][] logicMap1;

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

                    if (num == 2 || (num >= 11 && num <= 19) || num == 76) {
                        final Rectangle rect = new Rectangle(worldX, worldY, size, size);
                        //X DIEGO passo l'indice per sapere quale tipo di casella è per la piantagione
                        this.soilList.add(new Soil(rect, num));
                    }

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

    private boolean isSolid(int num) {
        return num == 3
            || num == 4
            || num == 6
            || (num >= 22 && num <= 26)
            || (num >= 31 && num <= 35)
            || (num >= 40 && num <= 45)
            || (num >= 48 && num <= 54)
            || (num >= 58 && num <= 60)
            || num == 66
            || (num >= 68 && num <= 70)
            || num == 72;
    }

    public List<Soil> getSoilList() {
        return this.soilList;
    }

    public List<SolidBlock> getSolidBlocks() {
        return this.solidBlocks;
    }

    //TO DO: ornamental
    public int getTileId(int row, int col) {
        if (row >= 0 && row < ImplViewGamePanel.MAX_WORLD_ROW && col >= 0 && col < ImplViewGamePanel.MAX_WORLD_COL) {
            return logicMap1[row][col];
        }
        return 0;
    }
}
