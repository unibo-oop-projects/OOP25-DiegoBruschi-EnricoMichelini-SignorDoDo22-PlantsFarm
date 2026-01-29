package it.unibo.plantsfarm.controller.gamepanel;

import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.model.camera.*;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.player.FactoryPlayer;
import it.unibo.plantsfarm.model.player.FarmerPlayer;
import it.unibo.plantsfarm.model.player.PlayersTypes;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.view.animation.ImplSelectorFrames;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public final class ImplControllerGamePanel extends Thread implements ControllerGamePanel {
    private ImplViewGamePanel view;
    private final  FactoryPlayer factoryPlayer = new FactoryPlayer();
    private  final static  int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private Player player;
    private Plant plant;
    private ImplSelectorFrames controllerAnimation;
    private Camera camera;
   
    public ImplControllerGamePanel() {
        setPlayer();
        this.player = getPlayer();
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

            view.show(player.getPosx(), player.getPosy(), camera.getCameraPosX(), camera.getCameraPosY(), player.listPod);
            try {
                Thread.sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
            } catch (InterruptedException e) {
                break;
            }
            
            controllerAnimation.update(now);
            player.updatePlayer(delta);
            player.updatePdod(now);
            camera.followPlayer();    
        }
    }
 
    @Override
    public void takeInput(final UserInput input) {
        if (input != null) {
            this.queue.add(input);
        }
    }
    
    @Override
    public void addView() {
        view = new ImplViewGamePanel();
        this.controllerAnimation = new ImplSelectorFrames(this);
        view.setSelectorFrames(controllerAnimation);
        view.setController(this);
        camera = new ImplCamera( view.getWidth(), view.getHeight());
        camera.setPlayer(player);
    }

    @Override
    public ImplViewGamePanel getView() {
        return this.view;
    }

    @Override
    public void setPlayer() {
        
        player = factoryPlayer.createPlayer(PlayersTypes.FARMER);
        this.player = new FarmerPlayer();
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
