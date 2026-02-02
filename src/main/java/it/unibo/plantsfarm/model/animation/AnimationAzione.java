package it.unibo.plantsfarm.model.animation;

import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.model.animation.api.Animation;

public final class AnimationAzione implements Animation {
    private final BufferedImage[] frames;
    private int frameIndex;
    private long lastFrameTimeNs;
    private final long frameDurationNs;
    private boolean playing;

    public AnimationAzione(final long frameDurationNs, final BufferedImage[] frames) {
        this.frameDurationNs = frameDurationNs;
        this.frames = frames.clone();
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

    @Override
    public boolean getisPlaying() {
        return playing;
    }
}
