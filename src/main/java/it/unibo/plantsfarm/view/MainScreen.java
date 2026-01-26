package it.unibo.plantsfarm.view;

import it.unibo.plantsfarm.view.menu.MenuPanel;
import it.unibo.plantsfarm.view.music.MusicPlayer;
import it.unibo.plantsfarm.view.utility.Texture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Manages the main screen window.
 */
public final class MainScreen {

    private static final String TITLE = "PlantsFarm";
    private static final String FONT_FAMILY = "SansSerif";
    private static final int FONT_SIZE = 24;

    private final MenuPanel menuPanel;
    private final MusicPlayer musicPlayer;
    private JFrame frame;
    private JLabel coinLabel;

    /**
     * Initializes the main screen components.
     */
    public MainScreen() {
        this.menuPanel = new MenuPanel();
        this.musicPlayer = new MusicPlayer();
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

        final JPanel gameContainer = new JPanel(new BorderLayout());
        gameContainer.setOpaque(false);

        final JPanel coinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        coinPanel.setOpaque(false);

        this.coinLabel = new JLabel(" 0");
        this.coinLabel.setIcon(Texture.COIN_ICON);
        this.coinLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        this.coinLabel.setForeground(Color.BLACK);

        coinPanel.add(this.coinLabel);
        gameContainer.add(coinPanel, BorderLayout.NORTH);

        this.frame.add(gameContainer, BorderLayout.CENTER);
        this.frame.setVisible(true);

        this.musicPlayer.playLoop("music/GardenMusic.wav");
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
        this.menuPanel.addExitListener(listener);
    }

    /**
     * Allows the controller to attach an action to the Encyclopedia button.
     *
     * @param listener The action to perform.
     */
    public void attachEncyclopediaListener(final ActionListener listener) {
        this.menuPanel.addEncyclopediaListener(listener);
    }

    /**
     * Allows the controller to attach an action to the Storage button.
     *
     * @param listener The action to perform.
     */
    public void attachStorageListener(final ActionListener listener) {
        this.menuPanel.addStorageListener(listener);
    }

    /**
     * Allows the controller to attach an action to the Shop button.
     *
     * @param listener The action to perform.
     */
    public void attachShopListener(final ActionListener listener) {
        this.menuPanel.addShopListener(listener);
    }

    /**
     * Closes the main window.
     */
    public void close() {
        if (this.frame != null) {
            this.frame.dispose();
        }
        this.musicPlayer.stop();
    }
}
