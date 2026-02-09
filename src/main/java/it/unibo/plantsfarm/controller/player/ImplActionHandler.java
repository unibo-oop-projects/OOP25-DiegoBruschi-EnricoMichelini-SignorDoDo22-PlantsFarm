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
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;


public final class ImplActionHandler implements ActionHandler{

    private final AbstractPlayer player;

    public ImplActionHandler(AbstractPlayer player){
        this.player = player;
    }


    @Override
    public void handleActionHoe(GardenController controllerGarden) {
        final Soil soil = controllerGarden.isPlayerOnSoil(player.getHitBox());
        if (controllerGarden.isPlayerOnSoil(player.getHitBox()) != null) {
            if(!soil.getIsPlanted() || soil.getPlant().isMature()){
                player.getInventory().useItem(HOE, ImplViewGamePanel.selectedPlant.getRarity());
                controllerGarden.pianta(ImplViewGamePanel.selectedPlant);
            }
        }
    }

    @Override
    public void handleWater(GardenController controllerGarden, Long now) {
        Soil soil = controllerGarden.isPlayerOnSoil(player.getHitBox());
        if (controllerGarden.isPlayerOnSoil(player.getHitBox()) != null) {
            if(soil.getPlant() != null && soil.getPlant().needsWater()){
                player.getInventory().useItem(WATERCAN, ImplViewGamePanel.selectedPlant.getRarity());
                controllerGarden.innaffia(now);
            }
        }
    }

    @Override
    public void handleAxe(GardenController controllerGarden) {
        Soil soil = controllerGarden.isPlayerOnSoil(player.getHitBox());
        if (controllerGarden.isPlayerOnSoil(player.getHitBox()) != null && soil.getIsPlanted()) {
            soil.removePlant();
        }
    }

    @Override
    public void updateDirection(UserInput input){
        player.setDirection(input);
    }

    @Override
    public void playerActionBuff(SpawningBuffsController controllerbuff){

        for (Buff buff : controllerbuff.getBuffList()) {
            if(player.getHitBox().intersects(buff.getBuffPosition()) || buff.getBuffPosition().contains(player.getHitBox())){
                controllerbuff.removeBuffFromMap(buff);
                player.getInventory().applyUpgrade();
            }
        }

    }


}
