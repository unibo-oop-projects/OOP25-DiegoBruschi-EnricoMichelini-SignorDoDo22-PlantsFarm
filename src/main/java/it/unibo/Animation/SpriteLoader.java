package it.unibo.Animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SpriteLoader {

    private final BufferedImage image;

    public SpriteLoader(final String resourcePath) {
        try {
            this.image = ImageIO.read(
                Objects.requireNonNull(SpriteLoader.class.getResourceAsStream(resourcePath), 
                "Risorsa non trovata: " + resourcePath));
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento immagine", e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
    
}