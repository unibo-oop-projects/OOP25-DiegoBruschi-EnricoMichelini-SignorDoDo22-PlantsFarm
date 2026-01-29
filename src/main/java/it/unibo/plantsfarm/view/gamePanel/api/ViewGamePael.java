package it.unibo.plantsfarm.view.gamePanel.api;

import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;

/**
 * 
 */
public interface ViewGamePael {
   
    /**
     * 
     * @param playerPosX
     * @param playerPosY
     * @param cameraX
     * @param cameraY
     */
    void show(double playerPosX, double playerPosY, int cameraX, int cameraY);

    /**
     * 
     * @param selector
     */
    void setSelectorFrames(SelectorFrames selector);

    /**
     * 
     * @param controller
     */
    void setController(ImplControllerGamePanel controller);
    
}
