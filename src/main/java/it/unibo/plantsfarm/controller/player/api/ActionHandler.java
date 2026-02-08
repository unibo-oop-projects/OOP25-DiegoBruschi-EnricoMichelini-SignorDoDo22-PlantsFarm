package it.unibo.plantsfarm.controller.player.api;

import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;

/**
 *
 */
public interface ActionHandler {

    /**
     *
     * @param input
     */
    void handleAction( UserInput input);

    /**
     *
     * @param controllerGarden
     */
    void handleActionHoe(GardenController controllerGarden);

    /**
     *
     * @param controllerGarden
     * @param now
     */
    void handleWater(GardenController controllerGarden, Long now);

    /**
     *
     * @param controllerGarden
     */
    void handleAxe(GardenController controllerGarden);

    /**
     *
     * @param input
     */
    void updateDirection(UserInput input);

    /**
     *
     * @param controllerbuff
     */
    void playerActionBuff(SpawningBuffsController controllerbuff);
}
