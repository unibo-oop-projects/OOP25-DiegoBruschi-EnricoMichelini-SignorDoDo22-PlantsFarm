package it.unibo.plantsfarm.view.gamePanel;

import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONHOE;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONWATER;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.UP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.plantsfarm.controller.action.SeedController;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.animation.api.AnimationFrames;
import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.plant.Plant;
import it.unibo.plantsfarm.model.plant.PlantEffect;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.gamePanel.api.ViewGamePanel;
import it.unibo.plantsfarm.view.inventario.UpgradeItemsView;
import it.unibo.plantsfarm.view.map.TileManager;
import it.unibo.plantsfarm.view.utility.SpriteLoader;
import it.unibo.plantsfarm.view.utility.Texture;

public final class ImplViewGamePanel extends JPanel implements ViewGamePanel {

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int SIDEBAR_WIDTH = 222;

    public static final int SCREEN_WIDTH = SCREEN_SIZE.width - SIDEBAR_WIDTH;
    public static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    private static final int VISIBLE_TILES_VERTICAL = 16;//22

    public static final int TILE_SIZE = SCREEN_HEIGHT / VISIBLE_TILES_VERTICAL;
    public static final int POD_SIZE = TILE_SIZE;

    public static final int PLAYER_SIZE = (int) (TILE_SIZE * 1.33);

    public static final int MAX_WORLD_COL = 66;
    public static final int MAX_WORLD_ROW = 23;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UP,
        KeyEvent.VK_A, LEFT,
        KeyEvent.VK_D, RIGHT,
        KeyEvent.VK_S, DOWN,
        KeyEvent.VK_E, ACTIONWATER,
        KeyEvent.VK_Q, ACTIONHOE
    );

  private final TileManager tileM;
  private int cameraX;
  private int cameraY;
  private double playerPosX;
  private double playerPosY;
  private ControllerGamePanel controller;
  private SelectorFrames selector;
  private UpgradeItemsView inventoryView;
  private boolean plantWindow = true;
  private List<Soil> soilList = List.of();
  private List<Buff> buffList = List.of();
  public static PlantType selectedPlant;

  public ImplViewGamePanel() {
    super();
    this.setLayout(null);
    this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setDoubleBuffered(true);
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.setBackground(Color.BLACK);
    this.tileM = new TileManager(this);
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(final KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_P) {
          new SeedController(selectedPlant -> {
          System.out.println("Selected plant: " + selectedPlant.getName());
          ImplViewGamePanel.selectedPlant = selectedPlant;
                    }, plantWindow).start();
                }

                if (KEY_MAPPER.containsKey(e.getKeyCode())) {
                    if (controller != null) controller.takeInput(KEY_MAPPER.get(e.getKeyCode()));
                    if (selector != null) selector.takeInput(KEY_MAPPER.get(e.getKeyCode()));
                }


        if(KeyEvent.VK_F == e.getKeyCode()) {
          inventoryView.update();
          inventoryView.setVisible(true);
        }
      }

      @Override
      public void keyReleased(final KeyEvent e) {
        if (KEY_MAPPER.containsKey(e.getKeyCode())) {
          if (controller != null) controller.takeInput(UserInput.FERMO);
        }
      }
    });
  }

    @Override
    public void show(final double posX, final double posY, final int camX, final int camY, final List<Soil> pods, final List<Buff> buffs) {
        SwingUtilities.invokeLater(() -> {
            this.playerPosX = posX;
            this.playerPosY = posY;
            this.cameraX = camX;
            this.cameraY = camY;
            this.soilList = pods;
            this.buffList = buffs;
            repaint();
        });
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2D = (Graphics2D) g;

        if (tileM != null) {
            tileM.drawTile(g2D, cameraX, cameraY);
        }

        for (Buff buff : buffList) {
            System.out.println(buffList.size());
            g2D.drawImage( new SpriteLoader("/plantStatus/Hearth.png").getImage(), buff.getBuffPosition().x - cameraX, buff.getBuffPosition().y - cameraY, 64,64, null);
        }   

            g2D.drawImage(selector.getCurrentImage(),
                (int) playerPosX - cameraX,
                (int) playerPosY - cameraY,
                PLAYER_SIZE,
                PLAYER_SIZE,
                null
            );


        if (soilList != null) {
            for (final Soil pod : soilList) {
                if (pod.getIsPlanted()) {
                    drawPlant(g2D, pod);
                }
            }
        }
        g2D.dispose();
    }

    private void drawPlant(final Graphics2D g2D, final Soil pod) {
        final Plant plant = pod.getPlant();
        final String plantName = plant.getName();
        final int stage = plant.getGrowthStage() + 1;

        final ImageIcon icon = Texture.getPlantStageIcon(plantName, stage);

        if (icon != null) {
            final Image image = icon.getImage();
            final double scale = 2;
            final int plantSize = (int) (POD_SIZE * scale);
            final int offset = (plantSize - POD_SIZE)/2;

            g2D.drawImage(image,
                pod.getCoordinate().x - cameraX - offset,
                pod.getCoordinate().y - cameraY - offset*2,
                plantSize,
                plantSize,
                null
            );

            ImageIcon statusIcon = null;

            //DA FARE: da spostare in controller
            if (plant.isMature()) {
                if (plant.isEdible()) {
                    statusIcon = Texture.READY_ICON;
                } else {
                    if (plant.getType().getEffectInfo() != null) {
                        final PlantEffect effectType = plant.getType().getEffectInfo().getType();
                        if (effectType == PlantEffect.GROWTH_SPEED) {
                            statusIcon = Texture.SPEED_ICON;
                        } else if (effectType == PlantEffect.BIG_HARVEST) {
                            statusIcon = Texture.HARVEST_ICON;
                        }
                    }
                }
            } else if (plant.needsWater()) {
                statusIcon = Texture.WATER_ICON;
            }

            if (statusIcon != null) {
                final int statusSize = Texture.STATUS_ICON_SIZE;
                final int iconX = (pod.getCoordinate().x - cameraX) - (statusSize / 3);
                final int iconY = (pod.getCoordinate().y - cameraY) - (statusSize);

                g2D.drawImage(statusIcon.getImage(), iconX, iconY, statusSize, statusSize, null);
            }
        }
    }

    @Override
    public void setController(final ImplControllerGamePanel controller) {
        this.controller = controller;
    }

    @Override
    public void setSelectorFrames(final SelectorFrames selectorFrames) {
        this.selector = selectorFrames;
    }

    public void setItemsView(final UpgradeItemsView inventoryView) {
      this.inventoryView = inventoryView;
    }
}
