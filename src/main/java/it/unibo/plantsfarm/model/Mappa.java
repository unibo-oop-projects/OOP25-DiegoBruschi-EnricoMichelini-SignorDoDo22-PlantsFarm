package it.unibo.plantsfarm.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStream;

public class Mappa {
    
    private int logicMap1 [][] = new int[21][66];
    private ModelTile logicaMap2 [][] = new ModelTile[21][66];
    

    public void loadMap(String filePath) {
    
        int posx = 0; 
        int posy = 0;

        try{
        InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
            for (int row = 0; row < 21; row++) {
                String line = br.readLine();
                for (int column = 0; column < 66; column++) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);
                    this.logicMap1[row][column] = num;
                    this.logicaMap2[row][column] = new ModelTile();
                    this.logicaMap2[row][column].isAttraversabile = false;
                    this.logicaMap2[row][column].rect = new Rectangle(posx,posy,48,48);
                    posy = posy + 48;
                    
                }
                posy = 0;
                posx = posx + 48;
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
            
    }

    public void stampaMatrice(){
        
        for(int i = 0; i < 21; i++){
            System.out.print("\n");
            for(int j = 0; j < 66; j++){
                System.out.print(logicaMap2[i][j].rect + " ");
            }
        }
    }

    public int[][] getMappa(){ return this.logicMap1; }


    public static void main(String[] args) {
        
        Mappa map = new Mappa();
        map.loadMap("/maps/map.txt");
        System.out.print(map.getMappa());
        map.stampaMatrice();

    }
}
