package it.unibo.plantsfarm.model.animation;

import java.awt.image.*;

import it.unibo.plantsfarm.model.animation.api.Animation;
import it.unibo.plantsfarm.model.animation.api.AnimationFrames;

public class AnimationCorsa implements Animation {

    private final BufferedImage[] frames;
    private int frameIndex;
    private long lastFrameTimeNs;
    private final long frameDurationNs;
    private boolean playing = false;

    public AnimationCorsa(final long frameDurationNs, BufferedImage[] frames) {
        this.frameDurationNs = frameDurationNs;
        this.frames = frames;
    }

    @Override
    public void start(final long nowNs) {
        if (playing) {              
            return;
        }
        playing = true;
        frameIndex = 0;
        lastFrameTimeNs = nowNs;
    }

    @Override
    public BufferedImage getCurrentFrame(final long nowNs) {
        
        if (!playing) {    
            return AnimationFrames.BASE;
        }
        
        if (nowNs - lastFrameTimeNs >= frameDurationNs) {
            frameIndex = (frameIndex + 1) % frames.length;
            lastFrameTimeNs = nowNs;
        }
        
        return frames[frameIndex]; 
    }

    @Override
    public boolean getisPlaying() {
        return playing;
    }
}