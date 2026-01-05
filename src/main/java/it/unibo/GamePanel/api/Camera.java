package it.unibo.GamePanel.api;

public interface Camera {
    
    /**
     * Calculate the position of camera based on player movement and position 
     * @return
     */
    public void followPlayer();
    
    /**
     * Return Position of the camera on the X axis
     * @return PositionX of camera
     */
    public int getCameraPosX();
    
    /**
     * Return Position of the camera on the Y axis
     * @return PositionY of camera 
     */
    public int getCameraPosY();

    
}
