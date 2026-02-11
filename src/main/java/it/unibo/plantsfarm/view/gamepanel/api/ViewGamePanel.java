package it.unibo.plantsfarm.view.gamepanel.api;

import java.util.List;
import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.tiles.Soil;

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
     * @param listBuff   List of Buffs
     */
    void show(double playerPosX, double playerPosY, int cameraX, int cameraY, List<Soil> listPod, List<Buff> listBuff);
}
