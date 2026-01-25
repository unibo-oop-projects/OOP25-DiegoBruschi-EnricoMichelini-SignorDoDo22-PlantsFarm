package it.unibo.plantsfarm.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStream;

public class Mappa {
    
    private int logicMap1 [][] = new int[21][66];
    private ModelTile logicaMap2 [][] = new ModelTile[21][66];
    

    public void loadMap(String filePath) {

    try {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

        for (int row = 0; row < 21; row++) {
            String line = br.readLine();
            String numbers[] = line.split(" ");
            
            for (int col = 0; col < 66; col++) {
                
                int num = Integer.parseInt(numbers[col]);
                
                this.logicMap1[row][col] = num;
                
                this.logicaMap2[row][col] = new ModelTile();
                
                int worldX = col * 48; 
                int worldY = row * 48;
                
                this.logicaMap2[row][col].rect = new Rectangle(worldX, worldY, 48, 48);

                if (num == 3 || num == 4 || num == 6 ) {
                    this.logicaMap2[row][col].isAttraversabile = false;
                }else{

                    this.logicaMap2[row][col].isAttraversabile = true;
                }
            }
        }
        br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModelTile[][] getLogicaMap2() {
        return this.logicaMap2;
    }

}
