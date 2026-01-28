package it.unibo.plantsfarm.view.animation.api;

import java.awt.image.BufferedImage;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;

public interface SelectorFrames {
 
    /**
     * Selecet the current Animation based on player action.
     * 
     * @param input
     */
    public void takeInput( UserInput input);

    /**
     * Calculate animation's next frame based on time rimaining.
     *  
     * @param now
     */
    public void update(Long now);

    /**
     * Returns the current Frame of the player.
     *  
     * @return CurrentFrame 
     */
    BufferedImage getCurrentImage();

}
