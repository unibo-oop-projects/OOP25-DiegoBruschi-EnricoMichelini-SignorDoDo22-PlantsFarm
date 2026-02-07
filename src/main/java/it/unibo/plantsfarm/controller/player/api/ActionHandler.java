package it.unibo.plantsfarm.controller.player.api;

import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.garden.GardenController;

public interface ActionHandler {

    void handleAction( UserInput input);

    void handleActionHoe(GardenController controllerGarden);

    void handleWater(GardenController controllerGarden, Long now);

    void handleAxe(GardenController controllerGarden);

    void updateDirection(UserInput input);
}
