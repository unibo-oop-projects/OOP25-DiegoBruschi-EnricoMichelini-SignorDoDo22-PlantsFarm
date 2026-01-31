package it.unibo.plantsfarm.view.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public final class SpriteLoader {

    private final BufferedImage image;

    public SpriteLoader(final String resourcePath) {
        try {
            this.image = ImageIO.read(
            Objects.requireNonNull(SpriteLoader.class.getResourceAsStream(resourcePath), 
            " Image not found " + resourcePath));
        } catch (final IOException e) {
            throw new RuntimeException("Loading Error", e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
