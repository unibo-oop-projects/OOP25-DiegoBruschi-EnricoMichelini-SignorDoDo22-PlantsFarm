package it.unibo.plantsfarm.view.menu;

import javax.swing.JDialog;

import it.unibo.plantsfarm.view.utility.WindowFactory;

/**
 * Manages the view for the Encyclopedia feature.
 */
public final class EncyclopediaScreen {

    private static final String TITLE = "Encyclopedia";
    private final JDialog dialog;

    /**
     * Initializes the encyclopedia window using the Factory.
     */
    public EncyclopediaScreen() {
        this.dialog = WindowFactory.createMenuWindow(TITLE);
    }

    /**
     * Displays the window.
     */
    public void show() {
        this.dialog.setVisible(true);
    }
}
