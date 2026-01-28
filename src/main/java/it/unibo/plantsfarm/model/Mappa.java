package it.unibo.plantsfarm.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class Mappa {
    
    private int logicMap1 [][] = new int[21][66];
    private List<Pod> pod = new LinkedList<>(List.of());

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
                int worldX = col * 48; 
                int worldY = row * 48;
                System.out.println(num);
                if (num == 18 ) {
                    Rectangle rect = new Rectangle(worldX, worldY, 48 * 3, 48 * 3);
                    pod.add(new Pod(rect));
                }
            }
        }
        br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Pod> getPod(){ return this.pod;}

}
