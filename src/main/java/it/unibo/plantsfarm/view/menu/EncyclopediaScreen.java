package it.unibo.plantsfarm.view.menu;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Manages the view for the Encyclopedia feature.
 */
public final class EncyclopediaScreen {

    private static final String TITLE = "Encyclopedia";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final JFrame frame;

    /**
     * Initializes the encyclopedia window.
     */
    public EncyclopediaScreen() {
        this.frame = new JFrame(TITLE);
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Displays the empty window.
     */
    public void show() {
        this.frame.setVisible(true);
    }
}
