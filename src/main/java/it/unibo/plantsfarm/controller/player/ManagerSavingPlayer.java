package it.unibo.plantsfarm.controller.player;

import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;

import java.nio.file.Path;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.utility.FileMemory;

public class ManagerSavingPlayer {

    final Path basePath = Path.of(System.getProperty("user.home"),".plantsfarm");
    final Memory memory = new FileMemory(basePath);

    public void loadManager(final ModelInventario inventarioPlayer, final AbstractPlayer abstractPlayer){
        final AbstractPlayer player = abstractPlayer;
        final ModelInventario inventario = inventarioPlayer;
        final SavePlayer savePlayer = new SavePlayer();
        if(player.getPlayerType() == FARMER) {
            savePlayer.load(memory, inventario);
        }
    }

    public void saveManager(final ModelInventario inventarioPlayer, final AbstractPlayer abstractPlayer){
        final AbstractPlayer player = abstractPlayer;
        final ModelInventario inventario = inventarioPlayer;
        final SavePlayer savePlayer = new SavePlayer();
        if(player.getPlayerType() == FARMER) {
            savePlayer.save(memory, inventario);
        }
    }

    public void resetSaving(){

    }
}
