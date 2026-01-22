package it.unibo.plantsfarm.view.Animation;

import static it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput.*;
import java.awt.image.BufferedImage;

import it.unibo.plantsfarm.controller.GamePanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.Animation.AnimationAzione;
import it.unibo.plantsfarm.model.Animation.AnimationCorsa;
import it.unibo.plantsfarm.model.Animation.api.Animation;
import it.unibo.plantsfarm.model.Animation.api.AnimationFrames;
import it.unibo.plantsfarm.view.Animation.api.SelectorFrames;

public class ImplSelectorFrames implements SelectorFrames {

    private ImplControllerGamePanel controller;
    private static final long FRAME_DURATION_NS = 120_000_000L;
    private AnimationAzione azione = new AnimationAzione(FRAME_DURATION_NS);
    private AnimationFrames frames = new AnimationFrames();
    private AnimationCorsa animationLeft = new AnimationCorsa(FRAME_DURATION_NS, frames.walkLeft);
    private AnimationCorsa animationUp = new AnimationCorsa(FRAME_DURATION_NS, frames.walkUp);
    private AnimationCorsa animationDown = new AnimationCorsa(FRAME_DURATION_NS, frames.walkDown);
    private AnimationCorsa animationRight = new AnimationCorsa(FRAME_DURATION_NS, frames.walkRight);

    private long nowNs;
    private Animation currentAnimation;
    private BufferedImage currentImage = frames.base;

    public ImplSelectorFrames(ImplControllerGamePanel controller){
        this.controller = controller;
    }

    @Override
    public void takeInput(UserInput input) {
    
        System.out.print(input + "\n");

        if( input == AZIONE ){
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

        if( input == FERMO && currentAnimation != azione ) {
            if(currentAnimation == azione && azione.isPlaying()){
                return;
            }else{
                this.currentImage = frames.base;
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
