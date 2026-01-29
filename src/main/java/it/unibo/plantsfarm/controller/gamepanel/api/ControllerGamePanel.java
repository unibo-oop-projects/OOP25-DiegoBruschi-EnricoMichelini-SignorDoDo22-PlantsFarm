package it.unibo.plantsfarm.controller.gamepanel.api;

import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;

public interface ControllerGamePanel {
    /**
     * Represents all possible inputs that can control the player's movement,
     * including the stationary state.
     */
    public enum UserInput {
        LEFT, RIGHT, DOWN, UP, FERMO, AZIONE 
    }

    /**
     * 
     * @param input
     */
    void takeInput(UserInput input);

    /**
     * 
     */
    void addView();

    /**
     * 
     * @return
     */
    ImplViewGamePanel getView();
    
    /**
     * 
     */
    void setPlayer();

    /**
     * 
     * @return
     */
    Player getPlayer();
}
