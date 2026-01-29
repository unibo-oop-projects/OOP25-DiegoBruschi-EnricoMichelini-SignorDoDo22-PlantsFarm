package it.unibo.plantsfarm.model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.cfg.ContextAttributes.Impl;

import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

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
                if (num == 2 || num == 11 || num == 12 || num == 13 || num == 14 || num == 15 || num == 16 || num == 17 || num == 18 || num == 19) {
                    Rectangle rect = new Rectangle(worldX, worldY, ImplViewGamePanel.tileSize, ImplViewGamePanel.tileSize);
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
