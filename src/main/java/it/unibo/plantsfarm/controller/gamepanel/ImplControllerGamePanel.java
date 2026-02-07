package it.unibo.plantsfarm.controller.gamepanel;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SaveController;
import it.unibo.plantsfarm.controller.inventario.ImplControllerInventario;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.camera.Camera;
import it.unibo.plantsfarm.model.camera.ImplCamera;
import it.unibo.plantsfarm.model.player.ImplFactoryPlayer;
import it.unibo.plantsfarm.model.player.PlayersTypes;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.animation.ImplSelectorFrames;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public final class ImplControllerGamePanel extends Thread implements ControllerGamePanel {
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private ImplViewGamePanel view;
    private final ImplFactoryPlayer factoryPlayer = new ImplFactoryPlayer();
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private AbstractPlayer player;
    private GardenController gardenController;
    private ImplSelectorFrames controllerAnimation;
    private Camera camera;
    private SaveController saver = new SaveController();
    //private final GameState gameState;
    private final ControllerInventario controllerInventario;

    public ImplControllerGamePanel(final GameState gameState) {
        //this.gameState = gameState;
        setPlayer();
        this.player = getPlayer();
        this.controllerInventario = new ImplControllerInventario(this.player);
        this.gardenController = new GardenController(gameState, this.player);
    }

   @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            final long now = System.currentTimeMillis();
            final long delta = now - lastTime;
            lastTime = now;
            final UserInput input = queue.poll();
            view.show(player.getPosx(), player.getPosy(), camera.getCameraPosX(),
            camera.getCameraPosY(), List.copyOf(gardenController.getSoilList()));
            try {
                Thread.sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
                if (input != null) {
                switch (input) {
                    case LEFT, RIGHT, DOWN, FERMO, UP -> player.setDirection(input);
                    case ACTIONHOE -> {
                        if (player.getInventory().useItem(HOE, ImplViewGamePanel.selectedPlant.getRarity())) {
                            gardenController.pianta(ImplViewGamePanel.selectedPlant);
                        }
                    }
                    case ACTIONWATER -> {
                        if (player.getInventory().useItem(WATERCAN, ImplViewGamePanel.selectedPlant.getRarity())) {
                            gardenController.innaffia(now);
                        }
                    }

                    case REMOVE_PLANT -> {
                        if(true){}
                    }
                }

                if(input != null)
                    controllerAnimation.takeInput(input);
            }
            } catch (final InterruptedException e) {
                break;
            }
            controllerAnimation.update(System.nanoTime());
            player.updatePlayer(delta);
            gardenController.updateSoil(now);
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
        this.view = new ImplViewGamePanel();
        this.controllerAnimation = new ImplSelectorFrames();
        this.controllerInventario.addView(this.view);
        this.view.setItemsView(controllerInventario.getView());
        view.setSelectorFrames(controllerAnimation);
        view.setController(this);
        camera = new ImplCamera(view.getWidth(), view.getHeight());
        camera.setPlayer(player);
    }

    @Override
    public ImplViewGamePanel getView() {
        return this.view;
    }

    @Override
    public void setPlayer() {
        this.player = factoryPlayer.createPlayer(PlayersTypes.FARMER);
    }

    @Override
    public AbstractPlayer getPlayer() {
        return this.player;
    }
}
