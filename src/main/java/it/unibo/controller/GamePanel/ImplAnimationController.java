package it.unibo.controller.GamePanel;

import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.AZIONE;
import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.FERMO;
import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput.UP;

import java.awt.image.BufferedImage;

import it.unibo.controller.Animation.SpriteLoader;
import it.unibo.controller.Animation.api.*;
import it.unibo.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.model.Player.BasePlayer;
import it.unibo.view.Animation.AnimationAzione;
import it.unibo.view.Animation.AnimationCorsa;
import it.unibo.view.Animation.api.Animation;
import it.unibo.view.Animation.api.AnimationFrames;


public class ImplAnimationController implements AnimationController {

    private ImplControllerGamePanel controllerGameScreen;
    private static final long FRAME_DURATION_NS = 120_000_000L;
    private AnimationAzione animation1 = new AnimationAzione(FRAME_DURATION_NS);
    private AnimationFrames frames = new AnimationFrames();
    private AnimationCorsa animationLeft = new AnimationCorsa(FRAME_DURATION_NS, frames.walkLeft);
    private AnimationCorsa animationUp = new AnimationCorsa(FRAME_DURATION_NS, frames.walkUp);
    private AnimationCorsa animationDown = new AnimationCorsa(FRAME_DURATION_NS, frames.walkDown);
    private AnimationCorsa animationRight = new AnimationCorsa(FRAME_DURATION_NS, frames.walkRight);

    public SpriteLoader normale = new SpriteLoader("/Player/tile001.png");
    private long nowNs;
    private Animation currentAnimation;
    private BufferedImage currentImage = frames.base;

    public ImplAnimationController(ImplControllerGamePanel controllerGamePanel){
        this.controllerGameScreen = controllerGamePanel;
    }

    @Override
    public void takeInput(UserInput input) {
    
        if( input == AZIONE ){
           nowNs = System.nanoTime();
           animation1.start(nowNs);
           currentAnimation = animation1;
        }

        if( input == UP ){
            nowNs = System.nanoTime();
            currentAnimation = animationUp;
            animationUp.start(nowNs);
        }

        if( input == RIGHT ){
            nowNs = System.nanoTime();
            currentAnimation = animationRight;
            animationRight.start(nowNs);
        }

        if( input == DOWN ){
            nowNs = System.nanoTime();
            currentAnimation = animationDown;
            animationDown.start(nowNs);
        }

        if( input == LEFT ){
            nowNs = System.nanoTime();
            animationLeft.start(nowNs);
            currentAnimation = animationLeft;
        }

        if( input == FERMO  ){
            if(currentAnimation == animation1 && animation1.isPlaying()){
            this.currentImage = normale.getImage();
            this.currentAnimation = null;
            }else{
                this.currentImage = normale.getImage();
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
