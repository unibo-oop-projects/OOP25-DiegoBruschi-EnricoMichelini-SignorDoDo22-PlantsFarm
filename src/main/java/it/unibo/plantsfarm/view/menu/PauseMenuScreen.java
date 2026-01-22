package it.unibo.plantsfarm.view.menu;

import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * View for the Pause Menu.
 */
public final class PauseMenuScreen {

    private static final String TITLE = "Pause Menu";
    private static final Color BG_COLOR = new Color(245, 245, 220);
    private static final int GAP = 15;
    private static final int PADDING = 30;
    private static final int BTN_WIDTH = 200;
    private static final int BTN_HEIGHT = 50;
    private static final int WIDTH = 350;
    private static final int HEIGHT = 400;

    private final JDialog screen;
    private final JPanel buttonPanel;

    /**
     * Creates the Pause Menu Screen.
     */
    public PauseMenuScreen() {
        this.screen = WindowFactory.createMenuWindow(TITLE);
        this.screen.setSize(WIDTH, HEIGHT);
        this.screen.setLocationRelativeTo(null);

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(3, 1, 0, GAP));
        this.buttonPanel.setBackground(BG_COLOR);
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        this.screen.add(this.buttonPanel);
    }

    private void addButton(final String text, final ActionListener listener) {
        final JButton button = ButtonFactory.createButton(text);
        button.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        button.addActionListener(listener);
        this.buttonPanel.add(button);
    }

    /**
     * Sets the action for the Resume button.
     *
     * @param listener The action listener.
     */
    public void setResumeButton(final ActionListener listener) {
        addButton("RESUME", listener);
    }

    /**
     * Sets the action for the Extra button.
     *
     * @param listener The action listener.
     */
    public void setExtraButton(final ActionListener listener) {
        addButton("EXTRA", listener);
    }

    /**
     * Sets the action for the Exit button.
     *
     * @param listener The action listener.
     */
    public void setExitButton(final ActionListener listener) {
        addButton("EXIT GAME", listener);
    }

    /**
     * Shows the pause menu window.
     */
    public void show() {
        this.screen.setVisible(true);
    }

    /**
     * Closes the pause menu window.
     */
    public void close() {
        this.screen.dispose();
    }
}
