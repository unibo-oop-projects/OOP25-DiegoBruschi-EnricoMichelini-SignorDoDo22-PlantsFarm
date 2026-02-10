package it.unibo.plantsfarm.controller.player;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;

import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.Soil;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * This class translates user input into actions performed by the player,
 * delegate the actual game logic to the {@link AbstractPlayer}.
 */
public final class ImplActionHandler implements ActionHandler {

    private final AbstractPlayer player;

    /**
     * Create a new action handler bound to specific player.
     *
     * @param player the player will receive and execute input actions
     */
    public ImplActionHandler(final AbstractPlayer player) {
        this.player = player;
    }

    @Override
    public void handleActionHoe(final GardenController controllerGarden) {
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && !soil.isPlanted()
        ) {
            player.getInventory().useItem(HOE, ImplViewGamePanel.selectedPlant.getRarity());
            controllerGarden.pianta(ImplViewGamePanel.selectedPlant);
        } else if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && soil.isPlanted() && soil.getPlant().isMature()
        ) {
            player.getInventory().useItem(HOE, ImplViewGamePanel.selectedPlant.getRarity());
            controllerGarden.pianta(ImplViewGamePanel.selectedPlant);
        }
    }

    @Override
    public void handleWater(final GardenController controllerGarden, final Long now) {
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && soil.getPlant() != null && soil.getPlant().needsWater()
        ) {
            player.getInventory().useItem(WATERCAN, ImplViewGamePanel.selectedPlant.getRarity());
            controllerGarden.innaffia(now);
        }
    }

    @Override
    public void handleAxe(final GardenController controllerGarden) {
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null && soil.isPlanted()) {
            soil.removePlant();
        }
    }

    @Override
    public void updateDirection(final UserInput input) {
        player.setDirection(input);
    }

    @Override
    public void playerActionBuff(final SpawningBuffsController controllerbuff) {

        for (final Buff buff : controllerbuff.getBuffList()) {
            if (player.getHitBox().intersects(buff.getBuffPosition()) || buff.getBuffPosition().contains(player.getHitBox())) {
                controllerbuff.removeBuffFromMap(buff);
                player.getInventory().applyUpgrade();
            }
        }

    }
}
