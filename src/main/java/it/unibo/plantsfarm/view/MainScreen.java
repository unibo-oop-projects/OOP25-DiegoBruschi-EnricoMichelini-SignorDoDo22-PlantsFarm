package it.unibo.plantsfarm.view;

import it.unibo.plantsfarm.controller.GamePanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.view.GamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.menu.MenuPanel;
import it.unibo.plantsfarm.view.utility.Texture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Manages the main screen window.
 */
public final class MainScreen {

    private static final String TITLE = "PlantsFarm";
    private static final String FONT_FAMILY = "SansSerif";
    private static final int FONT_SIZE = 24;
    private static final int UPPER_PANEL_WIDTH = 600;
    private static final int UPPER_PANEL_HEIGHT = 200;
    private static final int BUTTON_FONT_SIZE = 18;

    private final MenuPanel menuPanel;
    private ImplViewGamePanel gamePanel;
    private JFrame frame;
    private JLabel coinLabel;
    private JButton inventoryButton;
    private ImplControllerGamePanel controller;

    /**
     * Initializes the main screen components.
     */
    public MainScreen() {
        this.menuPanel = new MenuPanel();
        this.menuPanel.setFocusable(false);
    }

    /**
     * Creates and displays the main screen window.
     */
    public void createMainScreen() {
        this.frame = new JFrame(TITLE);

        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setSize(screenSize.width, screenSize.height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setResizable(false);
        this.frame.setUndecorated(true);

        this.frame.add(this.menuPanel, BorderLayout.EAST);

        final JLayeredPane layeredPane = new JLayeredPane();

        this.controller = new ImplControllerGamePanel();
        this.controller.addView();
        this.gamePanel = this.controller.getView();
        this.gamePanel.setBounds(0, 0, screenSize.width, screenSize.height);
        layeredPane.add(this.gamePanel, JLayeredPane.DEFAULT_LAYER);

        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        topPanel.setOpaque(false);
        topPanel.setFocusable(false);
        topPanel.setBounds(0, 0, UPPER_PANEL_WIDTH, UPPER_PANEL_HEIGHT);

        this.coinLabel = new JLabel(" 0");
        this.coinLabel.setIcon(Texture.COIN_ICON);
        this.coinLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        this.coinLabel.setForeground(Color.BLACK);

        this.inventoryButton = new JButton();
        this.inventoryButton.setIcon(Texture.INVENTORY_ICON);
        this.inventoryButton.setFont(new Font(FONT_FAMILY, Font.BOLD, BUTTON_FONT_SIZE));
        this.inventoryButton.setFocusable(false);
        this.inventoryButton.setContentAreaFilled(false);
        this.inventoryButton.setBorderPainted(false);
        this.inventoryButton.setOpaque(false);

        topPanel.add(this.coinLabel);
        topPanel.add(this.inventoryButton);

        layeredPane.add(topPanel, JLayeredPane.PALETTE_LAYER);

        this.frame.add(layeredPane, BorderLayout.CENTER);
        this.frame.setVisible(true);

        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocusInWindow();
        this.controller.start();
    }

    /**
     * Updates the coin label text.
     *
     * @param amount The current amount of coins.
     */
    public void updateCoinLabel(final int amount) {
        if (this.coinLabel != null) {
            this.coinLabel.setText(" " + amount);
        }
    }

    /**
     * Allows the controller to attach an action to the Exit button.
     *
     * @param listener The action to perform.
     */
    public void attachExitListener(final ActionListener listener) {
        this.menuPanel.addExitListener(e -> {
            listener.actionPerformed(e);
            this.gamePanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Encyclopedia button.
     *
     * @param listener The action to perform.
     */
    public void attachEncyclopediaListener(final ActionListener listener) {
        this.menuPanel.addEncyclopediaListener(e -> {
            listener.actionPerformed(e);
            this.gamePanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Storage button.
     *
     * @param listener The action to perform.
     */
    public void attachStorageListener(final ActionListener listener) {
        this.menuPanel.addStorageListener(e -> {
            listener.actionPerformed(e);
            this.gamePanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Shop button.
     *
     * @param listener The action to perform.
     */
    public void attachShopListener(final ActionListener listener) {
        this.menuPanel.addShopListener(e -> {
            listener.actionPerformed(e);
            this.gamePanel.requestFocusInWindow();
        });
    }

    /**
     * Allows the controller to attach an action to the Inventory button.
     *
     * @param listener The action to perform.
     */
    public void attachInventoryListener(final ActionListener listener) {
        if (this.inventoryButton != null) {
            this.inventoryButton.addActionListener(e -> {
                listener.actionPerformed(e);
                this.gamePanel.requestFocusInWindow();
            });
        }
    }

    /**
     * Closes the main window.
     */
    public void close() {
        if (this.frame != null) {
            this.frame.dispose();
        }
    }
}
