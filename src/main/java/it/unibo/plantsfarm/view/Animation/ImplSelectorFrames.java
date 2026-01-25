package it.unibo.plantsfarm.view.Animation;

import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.controller.GamePanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.Animation.AnimationAzione;
import it.unibo.plantsfarm.model.Animation.AnimationCorsa;
import it.unibo.plantsfarm.model.Animation.api.Animation;
import it.unibo.plantsfarm.model.Animation.api.AnimationFrames;
import it.unibo.plantsfarm.view.Animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.utility.AnimationTime;

public class ImplSelectorFrames implements SelectorFrames {

    private ImplControllerGamePanel controller;
    static private  final long FRAME_DURATION_NS = 120_000_000L;
    private AnimationAzione azione = new AnimationAzione(AnimationTime.FRAME_10_FPS);
    private AnimationCorsa animationLeft = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.walkLeft);
    private AnimationCorsa animationUp = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.walkUp);
    private AnimationCorsa animationDown = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.walkDown);
    private AnimationCorsa animationRight = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.walkRight);
    private long nowNs;
    private Animation currentAnimation;
    private BufferedImage currentImage = AnimationFrames.base;

    public ImplSelectorFrames(ImplControllerGamePanel controller){
        this.controller = controller;
    }

    @Override
    public void takeInput(final UserInput input) {

        final long nowNs = System.nanoTime();

        switch (input) {

            case AZIONE -> {
                azione.start(nowNs);
                currentAnimation = azione;
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

            case FERMO -> {
                if (currentAnimation == azione && azione.isPlaying()) {
                    return;
                }
                this.currentImage = AnimationFrames.base;
                this.currentAnimation = null;
            }
        }
    }

    public void update(Long now){
        
        long durationAnim = nowNs + FRAME_DURATION_NS;
        if(now <= durationAnim && currentAnimation != null ){
            this.currentImage = currentAnimation.getCurrentFrame(System.nanoTime());
        }
    }

    final public BufferedImage getCurrentImage(){ 
        return this.currentImage;
    }
}
