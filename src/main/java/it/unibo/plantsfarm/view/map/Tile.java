package it.unibo.plantsfarm.view.map;

import java.awt.image.BufferedImage;

/**
 * Represents a tile in the game map, used to draw the map by assigning an image to it.
 */
public class Tile {
    private BufferedImage image;

    /**
     * Gets the image of the tile.
     * 
     * @return The image of the tile.
     */
    public final BufferedImage getImage() {
        return this.image;
    }

    /**
     * Sets the image of the tile.
     * 
     * @param newImage The new image to set for the tile.
     */
    public final void setImage(final BufferedImage newImage) {
        if (newImage == null) {
            this.image = null;
        } else {
            this.image = new BufferedImage(
                newImage.getWidth(), 
                newImage.getHeight(), 
                newImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : newImage.getType()
            );
            java.awt.Graphics2D g2 = this.image.createGraphics();
            g2.drawImage(newImage, 0, 0, null);
            g2.dispose();
        }
    }
}
