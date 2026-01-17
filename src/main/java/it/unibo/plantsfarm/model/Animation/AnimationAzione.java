package it.unibo.plantsfarm.model.Animation;

import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.model.Animation.api.Animation;
import it.unibo.plantsfarm.model.Animation.api.AnimationFrames;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

public class AnimationAzione  implements Animation {

    private final BufferedImage[] frames;
    private int frameIndex;
    private long lastFrameTimeNs;
    private final long frameDurationNs;
    private boolean playing = false;
    private AnimationFrames animationframes = new AnimationFrames();
    BufferedImage base =  new SpriteLoader("/Player/tile001.png").getImage();

    public AnimationAzione(final long frameDurationNs) {
        this.frameDurationNs = frameDurationNs;
        frames = animationframes.water;
    }

    @Override
    public void start(final long nowNs) {
       
        frameIndex = 0;
        lastFrameTimeNs = nowNs;
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

    public boolean isPlaying() {
        return playing;
    }

    public boolean getisPlaying() {
        return playing;
    }
}
