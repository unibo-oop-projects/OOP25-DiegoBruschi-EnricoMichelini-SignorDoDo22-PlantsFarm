package it.unibo.plantsfarm.view.animation;

import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONHOE;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONWATER;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.FERMO;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.UP;
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
    private Animation currentAnimation;
    private BufferedImage currentImage = AnimationFrames.BASE;

    @Override
    public void takeInput(final UserInput input) {

        if (input == ACTIONHOE) {
           final long nowNs = System.nanoTime();
           animationHoe.start(nowNs);
           currentAnimation = animationHoe;
        }

        if (input == ACTIONWATER) {
           final long nowNs = System.nanoTime();
           animationWater.start(nowNs);
           currentAnimation = animationWater;
        }

        if (input == UP) {
            final long nowNs = System.nanoTime();
            currentAnimation = animationUp;
            animationUp.start(nowNs);
        }

        if (input == RIGHT) {
            final long nowNs = System.nanoTime();
            currentAnimation = animationRight;
            animationRight.start(nowNs);
        }

        if (input == DOWN) {
            final long nowNs = System.nanoTime();
            currentAnimation = animationDown;
            animationDown.start(nowNs);
        }

        if (input == LEFT) {
            final long nowNs = System.nanoTime();
            animationLeft.start(nowNs);
            currentAnimation = animationLeft;
        }

        if (input == FERMO) {
            if (animationHoe.equals(currentAnimation) && animationHoe.isPlaying()
                || (animationWater.equals(currentAnimation) && animationWater.isPlaying())) {
                return;
            }

            currentAnimation = null;
            currentImage = AnimationFrames.BASE;
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
