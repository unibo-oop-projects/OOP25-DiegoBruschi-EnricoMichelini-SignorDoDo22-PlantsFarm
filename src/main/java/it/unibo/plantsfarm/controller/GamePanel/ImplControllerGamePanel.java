package it.unibo.plantsfarm.controller.GamePanel;
import java.util.concurrent.LinkedBlockingQueue;

import it.unibo.plantsfarm.controller.Animation.ImplAnimationController;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.Player.BasePlayer;
import it.unibo.plantsfarm.view.ViewGamePanel;

public class ImplControllerGamePanel extends Thread {
    private ViewGamePanel view;
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private BasePlayer player;
    private ImplAnimationController controllerAnimation;
     
    
    public ImplControllerGamePanel() {

        this.player = new BasePlayer();
        setControllerAnimation();
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        
        
        while (true) {
            
            long now = System.currentTimeMillis();
            long delta = now - lastTime;
            lastTime = now;
            UserInput input = queue.poll();
            
            if (input != null) {
                
                player.setDirection(input);
                controllerAnimation.takeInput(input);
            }
            
            view.show(player.getPosx(), player.getPosy(), controllerAnimation.getCurrentImage());
            
            try {
                Thread.sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
            } catch (InterruptedException e) {
                break;
            }
            
            controllerAnimation.update(now);
            player.updatePlayer(delta);
            view.camera.followPlayer();            
        }
    }
 
    public void takeInput(final UserInput input) {
        if(input != null){
        this.queue.add(input);
        }
    }
    
    public void addView() {
        view = new ViewGamePanel();
        view.setController(this);
        view.camera.setPlayer(player);
        
    }

    public ViewGamePanel getView() {
        return this.view;
    }

    public void setPlayer() {
        this.player = new BasePlayer();
    }

    public BasePlayer getPlayer() {
        return this.player;
    }


    public void setControllerAnimation(){
        this.controllerAnimation = new ImplAnimationController(this);
    }

}
