package it.unibo.plantsfarm.view.gamePanel;

import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONHOE;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.ACTIONWATER;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.UP;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import it.unibo.plantsfarm.controller.action.SeedController;
import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.view.map.TileManager;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;
import it.unibo.plantsfarm.view.gamePanel.api.ViewGamePanel;
import it.unibo.plantsfarm.model.Soil;

public final class ImplViewGamePanel extends JPanel implements ViewGamePanel {
  public static final int MAP_RENDER_SCALE = 67;
  public static int orginalTileSize = Toolkit.getDefaultToolkit().getScreenSize().height / MAP_RENDER_SCALE;
  public static final int SCALE = 3;
  public static int tileSize = orginalTileSize * SCALE;
  public static final int POD_SIZE = ImplViewGamePanel.tileSize;
  public static final int PLAYER_SIZE = 64;
  public static final int MAXSCREENCOL = 66; 
  public static final int MAXSCREENROW = 23; 
  public static final int WORLD_WIDTH = tileSize * MAXSCREENCOL; 
  public static final int WORLD_HEIGHT = tileSize * MAXSCREENROW; 
  public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width - 222;
  public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

  private static final Map<Integer, ControllerGamePanel.UserInput> KEY_MAPPER = Map.of(
    KeyEvent.VK_W, UP,
    KeyEvent.VK_A, LEFT,
    KeyEvent.VK_D, RIGHT,
    KeyEvent.VK_S, DOWN,
    KeyEvent.VK_E, ACTIONWATER,
    KeyEvent.VK_Q, ACTIONHOE
  );

  private TileManager tileM;
  private int cameraX; 
  private int cameraY; 
  private double playerPosX;
  private double playerPosY;
  private ImplControllerGamePanel controller;
  private SelectorFrames selector;

  private boolean plantWindow = true; //da modificare in base alle piante da visualizzare
  private List<Soil> soilList = List.of();

  public ImplViewGamePanel() {
    super();
    this.requestFocus();
    this.setVisible(true);
    this.setDoubleBuffered(true);
    this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    this.setFocusable(true);
    this.requestFocusInWindow(true);
    this.setBackground(Color.BLACK);
    this.tileM = new TileManager(this);
    this.addKeyListener(new KeyAdapter() {

    @Override
    public void keyPressed(final KeyEvent e) {
      super.keyPressed(e);

      if (e.getKeyCode() == KeyEvent.VK_P) {
          new SeedController(selectedPlant -> {
            System.out.println("Selected plant: " + selectedPlant.getName());
          }, plantWindow).start();
          }

      if (KEY_MAPPER.containsKey(e.getKeyCode())) {
          controller.takeInput(KEY_MAPPER.get(e.getKeyCode()));
          selector.takeInput(KEY_MAPPER.get(e.getKeyCode()));
          }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
      if (KEY_MAPPER.containsKey(e.getKeyCode())) {
          controller.takeInput(UserInput.FERMO);
      }
    }
  });
  }

  @Override
  public void show(final double posX,
    final double posY,
    final int camX,
    final int camY,
    final List<Soil> pods
  ) {
    SwingUtilities.invokeLater(() -> {
      this.playerPosX = posX;
      this.playerPosY = posY;
      this.cameraX = camX;
      this.cameraY = camY;
      this.soilList = pods;
      repaint();
    });
  }

  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);
    final Graphics2D g2D = (Graphics2D) g;
    tileM.drawTile(g2D, cameraX, cameraY);
    g2D.drawImage(selector.getCurrentImage(),
      (int) playerPosX - cameraX,
      (int) playerPosY - cameraY,
      PLAYER_SIZE,
      PLAYER_SIZE,
      null
    );


    for (final Soil pod : soilList) {
     
      if (pod.getIsPlanted()) {
        final ImageIcon icon = Texture.getPlantStageIcon("Tomato", 3);
        final Image image = icon.getImage();
        pod.setPlanted(null); //da sostituire con pod.getPlant().getCurrentStageImage()
        g2D.drawImage(image, pod.getCoordinate().x - cameraX, pod.getCoordinate().y - cameraY, POD_SIZE, POD_SIZE, null);
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
}
