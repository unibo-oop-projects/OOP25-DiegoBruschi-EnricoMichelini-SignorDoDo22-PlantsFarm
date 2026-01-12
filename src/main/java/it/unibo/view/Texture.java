package it.unibo.view;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.net.URL;

public class Texture {

    //costant for proportions
    private static final double MENU_ICON_RATIO = 0.10; 
    private static final double PLANT_LIST_ICON_RATIO = 0.04;
    private static final double PLANT_DETAIL_ICON_RATIO = 0.20;

    private static final int MENU_ICON_DIMENSION = calculateSize(MENU_ICON_RATIO);

    public static final int PLANT_LIST_DIMENSION = calculateSize(PLANT_LIST_ICON_RATIO);
    public static final int PLANT_DETAIL_DIMENSION = calculateSize(PLANT_DETAIL_ICON_RATIO);

    private static int calculateSize(double ratio) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) (screenSize.getWidth() * ratio);
    }

    public static ImageIcon createIconFromPath(String fullPath, int width, int height) {

        URL imageURL = Texture.class.getClassLoader().getResource(fullPath);

        //Error
        if (imageURL == null) {
            try {
                java.io.File fileFisico = new java.io.File("src/" + fullPath);
                if (fileFisico.exists()) {
                    imageURL = fileFisico.toURI().toURL();
                    System.out.println("Recuperato da SRC: " + fullPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (imageURL == null) {
            System.err.println("Image not found: " + fullPath);
            return null;
        }

        ImageIcon originalIcon = new ImageIcon(imageURL);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE);
        return new ImageIcon(scaledImage);
    }

    public static ImageIcon createIcon(String fileName, int width, int height) {
        //Find the image inside "icons"
        return createIconFromPath("icons/" + fileName, width, height);
    }

    public static ImageIcon getPlantIcon(String fileName, int iconDimension) {
        return createIcon(fileName, iconDimension, iconDimension);
    }

    // Menu buttons
    public static final ImageIcon SHOP_ICON = createIcon("Shop.png", MENU_ICON_DIMENSION, MENU_ICON_DIMENSION);
    public static final ImageIcon WATERCAN_ICON = createIcon("Water_can.png", MENU_ICON_DIMENSION, MENU_ICON_DIMENSION);
    public static final ImageIcon SETTINGS_ICON = createIcon("Settings.png", MENU_ICON_DIMENSION, MENU_ICON_DIMENSION);
    public static final ImageIcon INVENTORY_ICON = createIcon("Inventory.png", MENU_ICON_DIMENSION, MENU_ICON_DIMENSION);
    public static final ImageIcon ENCYCLOPEDIA_ICON = createIcon("Encyclopedia.png", MENU_ICON_DIMENSION, MENU_ICON_DIMENSION);
}


