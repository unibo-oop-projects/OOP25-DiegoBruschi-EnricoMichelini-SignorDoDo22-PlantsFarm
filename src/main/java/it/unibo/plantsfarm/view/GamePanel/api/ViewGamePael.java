package it.unibo.plantsfarm.view.GamePanel.api;

import it.unibo.plantsfarm.view.Animation.api.SelectorFrames;

public interface ViewGamePael {
    
    void show(int playerPosX, int playerPosY, int cameraX, int cameraY);

    void setSelectorFrames(SelectorFrames selector);
    
}
