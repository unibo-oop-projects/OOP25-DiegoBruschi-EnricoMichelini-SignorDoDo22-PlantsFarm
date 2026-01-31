package it.unibo.plantsfarm.view.gamePanel.api;

import java.util.List;

import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.model.Soil;
import it.unibo.plantsfarm.view.animation.api.SelectorFrames;

/**
 * ViewGamePanel interface.
 * 
 */
public interface ViewGamePanel {
    /**
     * Show the GamePanel.
     * 
     * @param playerPosX Player position X
     * @param playerPosY Player position Y
     * @param cameraX    Camera position X
     * @param cameraY    Camera position Y
     * @param listPod    List of Soil
     */
    void show(double playerPosX, double playerPosY, int cameraX, int cameraY, List<Soil> listPod);

    /**
     * Set the SelectorFrames.
     * 
     * @param selector SelectorFrames
     */
    void setSelectorFrames(SelectorFrames selector);

    /**
     * Set the ControllerGamePanel.
     * 
     * @param controller ControllerGamePanel
     */
    void setController(ImplControllerGamePanel controller);
}
