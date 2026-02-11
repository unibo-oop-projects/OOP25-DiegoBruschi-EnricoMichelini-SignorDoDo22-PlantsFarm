package it.unibo.plantsfarm.view.animation.api;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;

/**
 * SelectorFrames interface.
 *
 */
public interface SelectorFrames {

    /**
     * Selecet the current Animation based on player action.
     *
     * @param input Input from user
     */
    void takeInput(UserInput input);

    /**
     * Calculate animation's next frame based on time rimaining.
     *
     * @param now Current time in milliseconds
     */
    void update(Long now);

    /**
     * Returns the current Frame of the player.
     *
     * @return CurrentFrame
     */
    BufferedImage getCurrentImage();

    void render(Graphics2D g2d, double posPlayerx, double posPlayery, int camerax, int cameray);

}
