package it.unibo.plantsfarm.model.animation;

import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.model.animation.api.Animation;

public final class AnimationHoe implements Animation {

   private final BufferedImage[] frames;
    private int frameIndex;
    private long lastFrameTimeNs;
    private final long frameDurationNs;
    private boolean playing;

    public AnimationHoe(final long frameDurationNs, final BufferedImage[] frames) {
        this.frameDurationNs = frameDurationNs;
        this.frames = frames;
    }

    @Override
    public void start(final long nowNs) {
        frameIndex = 0;
        lastFrameTimeNs = nowNs;
        this.playing = true;
    }

    @Override
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

    public boolean getisPlaying() {
        return playing;
    }

}
