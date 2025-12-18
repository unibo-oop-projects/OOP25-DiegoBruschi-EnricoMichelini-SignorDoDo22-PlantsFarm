package it.unibo.plantsfarm.view;

import java.awt.Font;
import javax.swing.JButton;

/**
 * Factory utility class for creating buttons.
 */
public final class ButtonFactory {

    private static final Font MAIN_FONT = new Font("Arial", Font.BOLD, 20);

    private ButtonFactory() {
    }

    /**
     * Creates a button.
     *
     * @param text The text to display on the button.
     *
     * @return A JButton.
     */
    public static JButton createMenuButton(final String text) {
        final JButton button = new JButton(text);
        button.setFont(MAIN_FONT);
        button.setFocusPainted(false);
        return button;
    }
}
