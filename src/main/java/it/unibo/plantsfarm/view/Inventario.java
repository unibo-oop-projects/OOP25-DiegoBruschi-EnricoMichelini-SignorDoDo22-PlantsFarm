package it.unibo.plantsfarm.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import it.unibo.plantsfarm.view.GamePanel.ImplViewGamePanel;

public class Inventario extends JPanel {
    
    boolean isVisible = false;
    ImplViewGamePanel view;
    int gap = 15;
    int slotSize = view.tileSize + view.tileSize / 2;
    ItemsView itemsImg = new ItemsView();
    Rectangle backgroundInventory;
    Rectangle borderInventory;
    
    List<Integer> inventoryPlayer = new LinkedList<>();

    public Inventario(ImplViewGamePanel viewGamePanel){
        this.view = viewGamePanel;
        setOpaque(false);
    }
    
    public void createInventory(Graphics2D g2d){
        
        int screenW = view.getWidth();
        int screenH = view.getHeight();
        int invW = view.tileSize * 7 + view.tileSize / 2;
        int invH = view.tileSize * 2;
        int invX = (screenW - invW) / 2;
        int invY = screenH - invH - 30;
        backgroundInventory = new Rectangle(invX, invY, invW, invH);
        borderInventory = new Rectangle(invX - gap / 2, invY - gap / 2, invW + gap, invH + gap);
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(10));
        g2d.draw(borderInventory);
        g2d.setColor(Color.gray);
        g2d.fillRect(backgroundInventory.x, backgroundInventory.y, backgroundInventory.width, backgroundInventory.height);
        g2d.setColor(Color.white);

        int w = 0;
        for(int i = 0; i < 4; i++){
            int x = backgroundInventory.x + gap + w;
            int y = backgroundInventory.y + gap;
            g2d.fillRect(x, y, slotSize, slotSize);
            g2d.drawImage(itemsImg.waterCanImage, x, y, slotSize, slotSize, null);
            w = w + slotSize + gap;
        }
    }
}