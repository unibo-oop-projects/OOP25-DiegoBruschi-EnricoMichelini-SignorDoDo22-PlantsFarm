package it.unibo.plantsfarm.controller.inventario;

import java.util.Map;
import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.inventario.UpgradeItemsView;

public final class ImplControllerInventario implements ControllerInventario {

    private final AbstractPlayer abstractPlayer;
    private UpgradeItemsView viewItemsInventory;

    public ImplControllerInventario(final AbstractPlayer player) {
        this.abstractPlayer = player;
    }

    public boolean isUpgredable(final Tooltype tool) {
        if (abstractPlayer.getInventory().isUpgredableItem(tool)) {
            return true;
        }
        return false;
    }

    public void pressUpgradeItem(final Tooltype tool) {
        abstractPlayer.getInventory().upgrade(tool);
        viewItemsInventory.update();
    }

    public UpgradeItemsView getView() {
        return this.viewItemsInventory;
    }

    public void addView(final ImplViewGamePanel gamePanel) {
        this.viewItemsInventory = new UpgradeItemsView(gamePanel, this);
    }

    public Map<Tooltype, ItemsFarm> getInventoryClone() {
        return this.abstractPlayer.getInventory().getInventorySnapshot();
    }
}
