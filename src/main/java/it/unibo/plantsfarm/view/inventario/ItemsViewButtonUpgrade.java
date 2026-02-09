package it.unibo.plantsfarm.view.inventario;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.plantsfarm.controller.inventario.api.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;

public class ItemsViewButtonUpgrade implements UpdatablePanel {

    private final ControllerInventario controllerInventario;
    private static final String UPGRADE_TEXT = " UPGRADE ";
    private final Map<Tooltype, JButton> progressButtonUpgradeMap = new LinkedHashMap<>();
    private final JPanel panel;

    public ItemsViewButtonUpgrade(ControllerInventario controllerInventario) {
        this.controllerInventario = controllerInventario;
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(3,1));
        createButtonItemsAction();
        update();
    }

     /**
     * Creates the upgrade action buttons for each tool.
     */
    public void createButtonItemsAction() {
        for (final Tooltype tool : Tooltype.values()) {
            final JButton upgrade = new JButton(UPGRADE_TEXT);
            upgrade.addActionListener(e -> {
                if (controllerInventario.isUpgredable(tool)) {
                    controllerInventario.pressUpgradeItem(tool);
                }
            });
            progressButtonUpgradeMap.put(tool, upgrade);
            panel.add(upgrade);
        }
    }

    @Override
    public void update() {
        for (final Tooltype tool : Tooltype.values()) {
            final JButton button = progressButtonUpgradeMap.get(tool);
            button.setEnabled(controllerInventario.isUpgredable(tool));
        }
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
