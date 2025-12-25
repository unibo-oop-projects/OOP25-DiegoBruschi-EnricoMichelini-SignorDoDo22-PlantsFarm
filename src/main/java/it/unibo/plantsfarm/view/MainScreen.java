package it.unibo.plantsfarm.view;

import it.unibo.plantsfarm.view.menu.MenuPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Manages the main screen window.
 */
public final class MainScreen {

    private static final String TITLE = "PlantsFarm";
    private final MenuPanel menuPanel;
    private JFrame frame;

    /**
     * Initializes the main screen components.
     */
    public MainScreen() {
        this.menuPanel = new MenuPanel();
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
        this.frame.setVisible(true);
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
    }
}
