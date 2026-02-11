package it.unibo.plantsfarm.controller.player;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.AXE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;

import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * This class translates user input into actions performed by the player,
 * delegate the actual game logic to the {@link Player}.
 */
public final class ImplActionHandler implements ActionHandler {

    @Override
    public void handleActionHoe(final GardenController controllerGarden, final PlantType selectedPlant, final Player player) {
        if (selectedPlant != null) {
            final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
            if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
                && !soil.isPlanted() && player.getInventory().useItem(HOE, selectedPlant.getRarity())
            ) {
                player.useItem(HOE, selectedPlant.getRarity());
                controllerGarden.pianta(selectedPlant);
            } else if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
                && soil.isPlanted() && soil.getPlant().isMature()
            ) {
                controllerGarden.pianta(selectedPlant);
            }
        }
    }

    @Override
    public void handleWater(final GardenController controllerGarden, final Long now,
        final PlantType selectedPlant, final Player player
    ) {
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && soil.getPlant() != null && soil.getPlant().needsWater()
            && player.getInventory().useItem(WATERCAN, soil.getPlant().getRarity())
        ) {
            controllerGarden.innaffia(now);
        }
    }

    @Override
    public void handleAxe(final GardenController controllerGarden, final Player player) {
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null && soil.isPlanted()
        && player.getInventory().useItem(AXE, soil.getPlant().getRarity())
        ) {
            soil.removePlant();
        }
    }

    @Override
    public void updateDirection(final UserInput input, final Player player) {
        player.setDirection(input);
    }

    @Override
    public void playerActionBuff(final SpawningBuffsController controllerbuff, final Player player) {
        for (final Buff buff : controllerbuff.getBuffList()) {
            if (player.getHitBox().intersects(buff.getBuffPosition()) || buff.getBuffPosition().contains(player.getHitBox())) {
                controllerbuff.removeBuffFromMap(buff);
                player.getInventory().applyUpgrade();
            }
        }

    }
}
