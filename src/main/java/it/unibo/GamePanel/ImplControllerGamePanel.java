package it.unibo.GamePanel;

import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.Animation.AnimationAttacco;
import it.unibo.Animation.ImplAnimationController;
import it.unibo.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.Player.BasePlayer;

public class ImplControllerGamePanel extends Thread {
    private ViewGamePanel view;
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private BasePlayer player;
    public ImplAnimationController controllerAnimation;
    public AnimationAttacco animazione = new AnimationAttacco(120_000_000L); 
    
    public ImplControllerGamePanel() {

        this.player = new BasePlayer();
        this.view = new ViewGamePanel();
        this.view.setController(this);
        setControllerAnimation();
        this.controllerAnimation.setPlayer(player);
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

            view.show(player.getPosx(), player.getPosy());
            
            try {
                Thread.sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
            } catch (InterruptedException e) {
                break;
            }

            controllerAnimation.update(now);
            player.updatePlayer(delta);
            
        }
    }

    /**
     * 
     * @param input
     */
    public void takeInput(final UserInput input) {
        if(input != null){
        this.queue.add(input);
        }
    }
    
    /**
     * Create the Main GamePanel. Only one GamePanel can be create at time
     */
    public void addView() {
        view = new ViewGamePanel();
        view.setController(this);
    }

    /**
     * 
     * @return
     */
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
