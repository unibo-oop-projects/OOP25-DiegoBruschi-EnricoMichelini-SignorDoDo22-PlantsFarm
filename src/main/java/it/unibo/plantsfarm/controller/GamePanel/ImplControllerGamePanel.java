package it.unibo.plantsfarm.controller.GamePanel;

import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.model.Mappa;
import it.unibo.plantsfarm.model.Camera.Camera;
import it.unibo.plantsfarm.model.Camera.ImplCamera;
import it.unibo.plantsfarm.model.Player.BasePlayer;
import it.unibo.plantsfarm.view.Animation.ImplSelectorFrames;
import it.unibo.plantsfarm.view.GamePanel.ImplViewGamePanel;

public final class ImplControllerGamePanel extends Thread implements ControllerGamePanel {
    private ImplViewGamePanel view;
    private  final static  int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private BasePlayer player;
    private ImplSelectorFrames controllerAnimation;
    private Camera camera;
    private final Mappa mappa = new Mappa();
    
    public ImplControllerGamePanel() {
        this.player = new BasePlayer();
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

            view.show(player.getPosx(), player.getPosy(), camera.getCameraPosX(), camera.getCameraPosY());
            try {
                Thread.sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
            } catch (InterruptedException e) {
                break;
            }
            
            controllerAnimation.update(now);
            player.updatePlayer(delta, mappa);
            camera.followPlayer();            
        }
    }
 
    public void takeInput(final UserInput input) {
        if (input != null) {
            this.queue.add(input);
        }
    }
    
    public void addView() {
        view = new ImplViewGamePanel();
        this.controllerAnimation = new ImplSelectorFrames(this);
        view.setSelectorFrames(controllerAnimation);
        view.setController(this);
        camera = new ImplCamera( view.getWidth(), view.getHeight());
        camera.setPlayer(player);
    }

    public ImplViewGamePanel getView() {
        return this.view;
    }

    public void setPlayer() {
        this.player = new BasePlayer();
    }

    public BasePlayer getPlayer() {
        return this.player;
    }
}
