package it.unibo.plantsfarm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Manages the main screen window.
 */
public final class MainScreen {

    private static final String TITLE = "PlantsFarm";

    /**
     * Creates and displays the main screen window.
     */
    public void createMainScreen() {
        final JFrame frame = new JFrame(TITLE);

        frame.setLayout(new BorderLayout());
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = screenSize.width;
        final int height = screenSize.height;

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        final MenuPanel menuPanel = new MenuPanel();
        frame.add(menuPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
