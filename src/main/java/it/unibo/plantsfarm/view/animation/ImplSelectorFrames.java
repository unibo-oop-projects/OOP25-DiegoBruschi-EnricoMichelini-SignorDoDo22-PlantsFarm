package it.unibo.plantsfarm.view.animation;

import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.animation.AnimationAzione;
import it.unibo.plantsfarm.model.animation.AnimationCorsa;
import it.unibo.plantsfarm.model.animation.api.Animation;
import it.unibo.plantsfarm.model.animation.api.AnimationFrames;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.utility.AnimationTime;

/**
 * Implementation of the class SelectorFrames.
 */
public final class ImplSelectorFrames implements SelectorFrames {

    private final AnimationAzione animationWater = new AnimationAzione(AnimationTime.FRAME_8_FPS, AnimationFrames.WATER);
    private final AnimationAzione animationHoe = new AnimationAzione(AnimationTime.FRAME_8_FPS, AnimationFrames.DIG);
    private final AnimationCorsa animationLeft = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.WALKLEFT);
    private final AnimationCorsa animationUp = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.WALKUP);
    private final AnimationCorsa animationDown = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.WALKDOWN);
    private final AnimationCorsa animationRight = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.WALKRIGHT);
    private final AnimationAzione animationAxe = new AnimationAzione(AnimationTime.FRAME_8_FPS, AnimationFrames.AXE);
    private Animation currentAnimation;
    private BufferedImage currentImage = AnimationFrames.BASE;

    @Override
    public void takeInput(final UserInput input) {
        final long nowNs = System.nanoTime();

        switch (input) {
            case ACTIONHOE -> {
                animationHoe.start(nowNs);
                currentAnimation = animationHoe;
            }
            case ACTIONWATER -> {
                animationWater.start(nowNs);
                currentAnimation = animationWater;
            }
            case UP -> {
                animationUp.start(nowNs);
                currentAnimation = animationUp;
            }
            case RIGHT -> {
                animationRight.start(nowNs);
                currentAnimation = animationRight;
            }
            case DOWN -> {
                animationDown.start(nowNs);
                currentAnimation = animationDown;
            }
            case LEFT -> {
                animationLeft.start(nowNs);
                currentAnimation = animationLeft;
            }
            case REMOVE_PLANT -> {
                animationAxe.start(nowNs);
                currentAnimation = animationAxe;
            }
            case FERMO -> {
                final boolean hoePlaying = currentAnimation.equals(animationHoe) && animationHoe.isPlaying();
                final boolean waterPlaying = currentAnimation.equals(animationWater) && animationWater.isPlaying();
                final boolean axePlaying = currentAnimation.equals(animationAxe) && animationAxe.isPlaying();
                if (hoePlaying || waterPlaying || axePlaying) {
                    return;
                }
                currentAnimation = null;
                currentImage = AnimationFrames.BASE;
            }
        }
    }

    @Override
    public void update(final Long nowNs) {
        if (currentAnimation != null) {
            currentImage = currentAnimation.getCurrentFrame(nowNs);

            if (!currentAnimation.isPlaying()) {
                currentAnimation = null;
                currentImage = AnimationFrames.BASE;
            }
        }
    }

    @Override
    public BufferedImage getCurrentImage() {
        return this.currentImage;
    }
}
