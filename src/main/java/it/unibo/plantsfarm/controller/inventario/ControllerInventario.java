package it.unibo.plantsfarm.controller.inventario;

import java.util.Map;

import it.unibo.plantsfarm.model.GameState;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.inventario.UpgradeItemsView;

public final class ControllerInventario {

    private final GameState gameState;
    private final AbstractPlayer abstractPlayer;
    private UpgradeItemsView viewItemsInventory;

    public ControllerInventario(final GameState gamestate, final AbstractPlayer player) {
        this.gameState = gamestate;
        this.abstractPlayer = player;
    }

    public void isReparable(int cost, Tooltype tool) {
        ModelInventario inv = abstractPlayer.getInventory();
        ItemsFarm item = inv.getItem(tool);
        System.out.println("Before "+  item.getLevel());
        if(gameState.spendCoins(cost)) {
            item.repair();
            System.out.println("Integrity after: " + item.getIntegrity());
        }
        viewItemsInventory.update();
    }

    public boolean controllWallet(int values) {
        System.out.println("Coin: " + gameState.getWallet() + " values: " + values);
        if(values > gameState.getWallet()){
            return false;
        }
        return true;
    }

    public UpgradeItemsView getView() {
        return this.viewItemsInventory;
    }

    public void addView(ImplViewGamePanel gamePanel) {
        this.viewItemsInventory = new UpgradeItemsView(gamePanel);
        this.viewItemsInventory.setControllerInventory(this);
    }

    public Map<Tooltype,ItemsFarm> getInventoryClone() {
        return this.abstractPlayer.getInventory().getInventorySnapshot();
     }
}
