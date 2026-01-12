package it.unibo.Animation.api;

import java.awt.image.BufferedImage;

public interface Animation {
    /**
     * 
     * @param now
     * @return 
     */
    public BufferedImage getCurrentFrame(long now);

    public void start(final long nowNs);
}
