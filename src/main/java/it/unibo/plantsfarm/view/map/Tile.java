package it.unibo.plantsfarm.view.map;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;

    public final BufferedImage getImage() {
        return this.image;
    }

    public final void setImage(final BufferedImage newImage) {
        this.image = newImage;
    }
}
