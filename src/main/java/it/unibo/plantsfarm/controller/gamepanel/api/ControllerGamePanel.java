package it.unibo.plantsfarm.controller.gamepanel.api;

import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Controller for the GamePanel.
 * This interface defines how the game view and the player model
 * are connected through user input.
 */
public interface ControllerGamePanel {

    /**
     * All possible inputs that can control the player.
     * Includes movement, actions and the idle state.
     */
    enum UserInput {
        LEFT, RIGHT, DOWN, UP, FERMO, ACTIONHOE, ACTIONWATER, REMOVE_PLANT
    }

    /**
     * Sends a user input to the controller.
     * This method is called by the view when the player presses a key.
     *
     * @param input the input received from the user
     */
    void takeInput(UserInput input);

    /**
     * Attaches or creates the game view for this controller.
     */
    void addView();

    /**
     * Returns the view currently attached to this controller.
     *
     * @return the active game view
     */
    ImplViewGamePanel getView();

    /**
     * Creates and sets the player using the Player Factory.
     * The Factory accepts the Enum PlayerType
     */
    void setPlayer();

    /**
     * Returns the player controlled by this controller.
     *
     * @return the current player
     */
    AbstractPlayer getPlayer();
}
