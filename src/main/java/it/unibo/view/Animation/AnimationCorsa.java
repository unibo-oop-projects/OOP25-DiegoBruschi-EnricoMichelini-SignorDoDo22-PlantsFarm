package it.unibo.view.Animation;
import java.awt.image.*;

import it.unibo.controller.Animation.SpriteLoader;
import it.unibo.controller.Animation.api.*;
import it.unibo.view.Animation.api.Animation;

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
            System.out.print("FERMATI");
            return base;
        
        }

        if (nowNs - lastFrameTimeNs >= frameDurationNs) {
            frameIndex = (frameIndex + 1) % frames.length;
            lastFrameTimeNs = nowNs;
        }
            return frames[frameIndex]; // <-- NON base
    }

    public boolean isPlaying() {
        return playing;
    }
}