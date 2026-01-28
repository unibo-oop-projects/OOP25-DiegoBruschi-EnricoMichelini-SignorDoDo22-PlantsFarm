package it.unibo.plantsfarm.view.animation;

import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.*;
import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.animation.AnimationAzione;
import it.unibo.plantsfarm.model.animation.AnimationCorsa;
import it.unibo.plantsfarm.model.animation.api.Animation;
import it.unibo.plantsfarm.model.animation.api.AnimationFrames;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.utility.AnimationTime;

public class ImplSelectorFrames implements SelectorFrames {

    private ImplControllerGamePanel controller;
    private final  AnimationAzione azione = new AnimationAzione(AnimationTime.FRAME_10_FPS, AnimationFrames.WATER);
    private final  AnimationCorsa animationLeft = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.WALKLEFT);
    private final AnimationCorsa animationUp = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.WALKUP);
    private final AnimationCorsa animationDown = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.WALKDOWN);
    private final AnimationCorsa animationRight = new AnimationCorsa(AnimationTime.FRAME_10_FPS, AnimationFrames.WALKRIGHT);

    private long nowNs;
    private Animation currentAnimation;
    private BufferedImage currentImage = AnimationFrames.BASE;

    public ImplSelectorFrames(ImplControllerGamePanel controller){
        this.controller = controller;
    }

    @Override
    public void takeInput(final UserInput input) {
        

        if( input == AZIONE ) {
           nowNs = System.nanoTime();
           azione.start(nowNs);
           currentAnimation = azione;
        }

        if( input == UP ) {
            nowNs = System.nanoTime();
            currentAnimation = animationUp;
            animationUp.start(nowNs);
        }

        if( input == RIGHT ) {
            nowNs = System.nanoTime();
            currentAnimation = animationRight;
            animationRight.start(nowNs);
        }

        if( input == DOWN ) {
            nowNs = System.nanoTime();
            currentAnimation = animationDown;
            animationDown.start(nowNs);
        }

        if( input == LEFT ) {
            nowNs = System.nanoTime();
            animationLeft.start(nowNs);
            currentAnimation = animationLeft;
        }
        
        if( input == FERMO && currentAnimation != azione && currentAnimation != null ) {
            if(currentAnimation.equals(azione)  && azione.getisPlaying()){
                return;
            }else{
                this.currentImage = AnimationFrames.BASE;
                this.currentAnimation = null;
            }
        }

    }
 
    public void update(Long now){
        
        long durationAnim = nowNs + AnimationTime.FRAME_10_FPS;
        if(now <= durationAnim && currentAnimation != null ){
            this.currentImage = currentAnimation.getCurrentFrame(System.nanoTime());
        }
    }

    final public BufferedImage getCurrentImage(){ 
        return this.currentImage;
    }
  
}