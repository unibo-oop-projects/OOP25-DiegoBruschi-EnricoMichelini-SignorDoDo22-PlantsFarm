package it.unibo.plantsfarm.view.utility;

import it.unibo.plantsfarm.view.music.MusicPlayer;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Factory utility class for creating buttons.
 */
public final class ButtonFactory {

    private static final Font MAIN_FONT = new Font("Arial", Font.BOLD, 20);
    private static final MusicPlayer AUDIO = new MusicPlayer();

    private ButtonFactory() {
    }

    /**
     * Creates a standard text button.
     *
     * @param text The text to display on the button.
     * @return A JButton.
     */
    public static JButton createButton(final String text) {
        return configureButton(new JButton(text));
    }

    /**
     * Creates an icon button.
     *
     * @param icon The icon to display.
     * @return A JButton containing the icon.
     */
    public static JButton createMenuButton(final ImageIcon icon) {
        final JButton button = new JButton(icon);
        button.addActionListener(e -> AUDIO.playEffect("music/menuSound/click.wav"));
        return button;
    }

    /**
     * Helper method to configure the standard text button.
     *
     * @param button The button to configure.
     * @return The configured button.
     */
    private static JButton configureButton(final JButton button) {
        button.setFont(MAIN_FONT);
        button.setFocusPainted(false);
        //button.addActionListener(e -> AUDIO.playEffect("music/menuSound/click.wav"));
        return button;
    }
}
