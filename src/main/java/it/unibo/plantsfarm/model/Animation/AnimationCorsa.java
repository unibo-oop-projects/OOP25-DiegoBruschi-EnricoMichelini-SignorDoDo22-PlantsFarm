package it.unibo.plantsfarm.model.Animation;

import java.awt.image.*;
import it.unibo.plantsfarm.model.Animation.api.Animation;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

public class AnimationCorsa implements Animation {

    private final BufferedImage[] frames;
    private final BufferedImage base =  new SpriteLoader("/Player/tile001.png").getImage();
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

    public void stop() {
        playing = false;
        frameIndex = 0;
    }

    @Override
    public BufferedImage getCurrentFrame(final long nowNs) {
        
        if (!playing) {    
            return base;
        }

        if (nowNs - lastFrameTimeNs >= frameDurationNs) {
            frameIndex = (frameIndex + 1) % frames.length;
            lastFrameTimeNs = nowNs;
        }
            return frames[frameIndex]; 
    }

    public boolean isPlaying() {
        return playing;
    }
}