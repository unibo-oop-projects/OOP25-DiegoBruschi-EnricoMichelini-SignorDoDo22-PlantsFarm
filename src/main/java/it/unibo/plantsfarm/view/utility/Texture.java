package it.unibo.plantsfarm.view.utility;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Utility class for loading game textures.
 * It manages resources.
 */
public final class Texture {

    public static final String MENU_PATH = "icons/menuIcon/";
    public static final double MENU_ICON_RATIO = 0.10;
    public static final int MENU_ICON_SIZE = calculateSize(MENU_ICON_RATIO);

    public static final String PLANT_PATH = "icons/plantIcon/";
    public static final double PLANT_ICON_RATIO = 0.05;
    public static final int PLANT_ICON_SIZE = calculateSize(PLANT_ICON_RATIO);

    public static final String STAGE_PATH = "plantsStage/";
    public static final double STAGE_ICON_RATIO = 0.20;
    public static final int STAGE_ICON_SIZE = calculateSize(STAGE_ICON_RATIO);

    public static final ImageIcon ENCYCLOPEDIA_ICON = loadMenuIcon("Encyclopedia.png");
    public static final ImageIcon SHOP_ICON = loadMenuIcon("Shop.png");
    public static final ImageIcon STORAGE_ICON = loadMenuIcon("Storage.png");
    public static final ImageIcon SETTINGS_ICON = loadMenuIcon("Settings.png");
    public static final ImageIcon GIFT_ICON = loadMenuIcon("Gift.png");
    public static final ImageIcon RARE_GIFT_ICON = loadMenuIcon("RareGift.png");
    public static final ImageIcon EPIC_GIFT_ICON = loadMenuIcon("EpicGift.png");
    public static final ImageIcon LEGENDARY_GIFT_ICON = loadMenuIcon("LegendaryGift.png");
    public static final ImageIcon COIN_ICON = loadMenuIcon("Coin.png");

    public static final ImageIcon RESUME_ICON = loadMenuIcon("Resume.png");
    public static final ImageIcon COMMANDS_ICON = loadMenuIcon("Commands.png");
    public static final ImageIcon RESET_ICON = loadMenuIcon("Reset.png");
    public static final ImageIcon EXTRA_ICON = loadMenuIcon("Extra.png");
    public static final ImageIcon CREDITS_ICON = loadMenuIcon("Credit.png");
    public static final ImageIcon EXIT_ICON = loadMenuIcon("Exit.png");

    private Texture() {
        // Utility class constructor
    }

    /**
     * Gets a plant icon dynamically.
     *
     * @param plantName The name of the plant.
     * @return The ImageIcon.
     */
    public static ImageIcon getPlantIcon(final String plantName) {
        final String fullPath = PLANT_PATH + plantName + ".png";
        return loadAndScale(fullPath, PLANT_ICON_SIZE, PLANT_ICON_SIZE);
    }

    /**
     * Gets a plant stage icon dynamically.
     *
     * @param plantName The name of the plant.
     * @param stage The stage number.
     * @return The ImageIcon.
     */
    public static ImageIcon getPlantStageIcon(final String plantName, final int stage) {
        final String fullPath = STAGE_PATH + plantName + "Stage/" + plantName + stage + ".png";
        return loadAndScale(fullPath, STAGE_ICON_SIZE, STAGE_ICON_SIZE);
    }

    /**
     * Calculates the icon size based on screen width.
     *
     * @param ratio the ratio of the screen width.
     * @return the calculated size.
     */
    private static int calculateSize(final double ratio) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) (screenSize.width * ratio);
    }

    /**
     * Loads an icon from the menu folder.
     *
     * @param fileName The name of the file.
     * @return The ImageIcon.
     */
    private static ImageIcon loadMenuIcon(final String fileName) {
        final String fullPath = MENU_PATH + fileName;
        return loadAndScale(fullPath, MENU_ICON_SIZE, MENU_ICON_SIZE);
    }

    /**
     * Generic method to load and scale an image from resources.
     *
     * @param path The path to the resource.
     * @param width The width.
     * @param height The  height.
     * @return The ImageIcon, or null if not found.
     */
    private static ImageIcon loadAndScale(final String path, final int width, final int height) {
        final URL imageURL = ClassLoader.getSystemResource(path);

        if (imageURL == null) {
            return null;
        }

        final ImageIcon original = new ImageIcon(imageURL);
        final Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE);
        return new ImageIcon(scaled);
    }
}
