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
    private long nowNs;
    private Animation currentAnimation;
    private BufferedImage currentImage = AnimationFrames.BASE;

    @Override
    public void takeInput(final UserInput input) {

        if (input == ACTIONHOE) {
           nowNs = System.nanoTime();
           animationHoe.start(nowNs);
           currentAnimation = animationHoe;
        }

        if (input == ACTIONWATER) {
           nowNs = System.nanoTime();
           animationWater.start(nowNs);
           currentAnimation = animationWater;
        }

        if (input == UP) {
            nowNs = System.nanoTime();
            currentAnimation = animationUp;
            animationUp.start(nowNs);
        }

        if (input == RIGHT) {
            nowNs = System.nanoTime();
            currentAnimation = animationRight;
            animationRight.start(nowNs);
        }

        if (input == DOWN) {
            nowNs = System.nanoTime();
            currentAnimation = animationDown;
            animationDown.start(nowNs);
        }

        if (input == LEFT) {
            nowNs = System.nanoTime();
            animationLeft.start(nowNs);
            currentAnimation = animationLeft;
        }

        if (input == FERMO
            && currentAnimation != animationWater
            && currentAnimation != null
            && currentAnimation != animationHoe
        ) {
            if (currentAnimation.equals(animationHoe)
                && animationHoe.getisPlaying()
                || currentAnimation.equals(animationWater)
                && animationWater.getisPlaying()
            ) {
                return;
            } else {
                this.currentImage = AnimationFrames.BASE;
                this.currentAnimation = null;
            }
        }

    }

    @Override
    public void update(final Long nowNs) {
        if (currentAnimation != null) {
            currentImage = currentAnimation.getCurrentFrame(nowNs);

            if (!currentAnimation.getisPlaying()) {
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
