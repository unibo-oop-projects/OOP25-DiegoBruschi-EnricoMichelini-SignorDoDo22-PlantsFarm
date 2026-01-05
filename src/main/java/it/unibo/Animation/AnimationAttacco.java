package it.unibo.Animation;

import java.awt.image.BufferedImage;

public class AnimationAttacco {

    private  BufferedImage[] frames;
    private int frameIndex;
    private long lastTime;
    private final long frameDuration;
    private long now = System.nanoTime();
   
    public AnimationAttacco(final long frameDuration) {

        this.frameDuration = frameDuration;
        frames = new BufferedImage[] {
        new SpriteLoader("/Player/tile042.png").getImage(),
        new SpriteLoader("/Player/tile043.png").getImage(),
        new SpriteLoader("/Player/tile044.png").getImage(),
        new SpriteLoader("/Player/tile045.png").getImage()
        };
    }

    /**
     * 
     * @param now
     * @return
     */
    public BufferedImage getCurrentFrame(final long now) {
        System.out.println("delta=" + (now - lastTime));
        if (now - lastTime >= frameDuration) {
            System.out.println("Sto disegnando \n");
            System.out.println("current frame (SONO QUA DENTRO ANIMATION )" + frameIndex + "\n");
            frameIndex = (frameIndex + 1) % frames.length;
            lastTime = now; 
        }
        return frames[frameIndex];
    }

}
