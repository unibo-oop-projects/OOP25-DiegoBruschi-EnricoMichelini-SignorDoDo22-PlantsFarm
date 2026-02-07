package it.unibo.plantsfarm.view.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Utility class responsible for loading sprite images from the classpath.
 * This class loads a  BufferedImage using a resource path and keeps it.
 * It throws a RuntimeException if the image cannot be found or loaded.
 *
 */
public final class SpriteLoader {

    /**
     * The loaded sprite image.
     */
    private final BufferedImage image;

    /**
     * Creates a new {@code SpriteLoader} and loads the image located at the given resource path.
     *
     * @param resourcePath the path of the image resource inside the classpath
     * @throws RuntimeException if the resource is not found or cannot be loaded
     */
    public SpriteLoader(final String resourcePath) {
        try {
            this.image = ImageIO.read(
            Objects.requireNonNull(SpriteLoader.class.getResourceAsStream(resourcePath),
            " Image not found " + resourcePath));
        } catch (final IOException e) {
            throw new IllegalArgumentException("Loading Error", e);
        }
    }

    /**
     * Returns the loaded sprite image.
     *
     * @return the BufferedImage loaded from the resource path
     */
    public BufferedImage getImage() {
        return image;
    }
}
