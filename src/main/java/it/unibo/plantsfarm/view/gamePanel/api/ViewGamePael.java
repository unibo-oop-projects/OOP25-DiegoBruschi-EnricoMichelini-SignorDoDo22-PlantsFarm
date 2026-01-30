package it.unibo.plantsfarm.view.gamePanel.api;

import java.util.List;

import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.model.Pod;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;

/**
 * 
 */
public interface ViewGamePael {
    
    void show(double playerPosX, double playerPosY, int cameraX, int cameraY, List<Pod> listPod);

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
