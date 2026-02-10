package it.unibo.plantsfarm.controller.gamepanel;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.controller.player.ImplActionHandler;
import it.unibo.plantsfarm.controller.player.ManagerSavingPlayer;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
import it.unibo.plantsfarm.controller.inventario.ImplControllerInventario;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.camera.ImplCamera;
import it.unibo.plantsfarm.model.garden.CollisionDetector;
import it.unibo.plantsfarm.model.garden.SoilSaving;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.InventoryFactory;
import it.unibo.plantsfarm.model.player.ImplFactoryPlayer;
import it.unibo.plantsfarm.model.player.PlayersTypes;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.TileMap;
import it.unibo.plantsfarm.view.animation.ImplSelectorFrames;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Implementation of the ControllerGamePanel interface, responsible for managing the game loop,
 * handling user input, and coordinating interactions between the model and view components.
 */
public final class ImplControllerGamePanel extends Thread implements ControllerGamePanel {
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private ImplViewGamePanel view;
    private final ImplFactoryPlayer factoryPlayer = new ImplFactoryPlayer();
    private final BlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private AbstractPlayer player;
    private final GardenController gardenController;
    private ImplSelectorFrames controllerAnimation;
    private ImplCamera camera;
    private final TileMap map;
    private final CollisionDetector collisionDetector;
    private final ActionHandler actionHandler;
    private final SoilSaving saver = new SoilSaving();
    private final SpawningBuffsController spawningBuffsController;
    private final ControllerInventario controllerInventario;
    private final ManagerSavingPlayer managerSavingPlayer;
    private ModelInventario modelInventario;

    /**
     * Creates a new ImplControllerGamePanel with the specified GameState.
     *
     * @param gameState The initial state of the game.
     */
    public ImplControllerGamePanel(final GameState gameState) {
        this.map = new TileMap();
        this.map.loadMap("/maps/map.txt");
        setPlayer();
        actionHandler = new ImplActionHandler();
        this.controllerInventario = new ImplControllerInventario(this.player);
        this.gardenController = new GardenController(gameState, this.player);
        this.collisionDetector = new CollisionDetector(this.player);
        this.spawningBuffsController = new SpawningBuffsController(map);
        this.managerSavingPlayer = new ManagerSavingPlayer();
        setInventario();
        managerSavingPlayer.loadManager(modelInventario, player);
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
            camera.getCameraPosY(), List.copyOf(gardenController.getSoilList()),
            List.copyOf(spawningBuffsController.getBuffList()));
            try {
                sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
                if (input != null) {
                    switch (input) {
                    case DOWN, UP, RIGHT, LEFT, FERMO -> actionHandler.updateDirection(input, player);
                    case ACTIONHOE -> {
                        actionHandler.handleActionHoe(gardenController, player);
                        saver.saveGame(gardenController.getSoilList());
                        managerSavingPlayer.saveManager(player.getInventory(), player);
                    }
                    case ACTIONWATER -> {
                        actionHandler.handleWater(gardenController, now, player);
                        saver.saveGame(gardenController.getSoilList());
                        managerSavingPlayer.saveManager(player.getInventory(), player);
                    }
                    case REMOVE_PLANT -> {
                        actionHandler.handleAxe(gardenController, player);
                        saver.saveGame(gardenController.getSoilList());
                        managerSavingPlayer.saveManager(player.getInventory(), player);
                    }
                }
                    controllerAnimation.takeInput(input);
                }
            } catch (final InterruptedException e) {
                break;
            }

            spawningBuffsController.updateUpGrade();
            actionHandler.playerActionBuff(spawningBuffsController, player);
            collisionDetector.collisionDetection();
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
    public void openInventory() {
        this.controllerInventario.getView().setVisible(true);
    }

    @Override
    public void setInventario() {
        InventoryFactory inventoryFact = new InventoryFactory();
        this.modelInventario = inventoryFact.createInventory(PlayersTypes.FARMER);
    }
}
