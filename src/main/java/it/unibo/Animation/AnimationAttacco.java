package it.unibo.Animation;

import java.awt.image.BufferedImage;
import it.unibo.Animation.api.*;;

public class AnimationAttacco  implements Animation {

    private final BufferedImage[] frames;
    private int frameIndex;
    private long lastFrameTimeNs;
    private final long frameDurationNs;
    private boolean playing = false;
    private AnimationFrames animationframes = new AnimationFrames();

    public AnimationAttacco(final long frameDurationNs) {
        this.frameDurationNs = frameDurationNs;
        frames = animationframes.water;
    }

    @Override
    public void start(final long nowNs) {
       
        frameIndex = 0;
        lastFrameTimeNs = nowNs;
    }

    public BufferedImage getCurrentFrame(final long nowNs) {
        
        if (nowNs - lastFrameTimeNs >= frameDurationNs) {
            frameIndex++;
            lastFrameTimeNs = nowNs;
            
            if (frameIndex >= frames.length) {
                frameIndex = frames.length - 1;
                playing = false; 
            }
        }
        return frames[frameIndex];
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean getisPlaying() {
        return playing;
    }
}
