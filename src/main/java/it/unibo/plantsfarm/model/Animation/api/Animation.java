package it.unibo.plantsfarm.model.Animation.api;

import java.awt.image.BufferedImage;

/**
 * This class is used for define the generic Animation and its base methods.
 */
public interface Animation {
    /**
     * this method return the current frame of the player animation.
     * 
     * @param now CurrentTime, is used for calculate the next frame based on begin time 
     * 
     * @return CurrentImage 
     */
    BufferedImage getCurrentFrame(long now);

    /**
     * Start animation from time nowNs.
     * 
     * @param nowNs time begin 
     */
    void start(long nowNs);
}
