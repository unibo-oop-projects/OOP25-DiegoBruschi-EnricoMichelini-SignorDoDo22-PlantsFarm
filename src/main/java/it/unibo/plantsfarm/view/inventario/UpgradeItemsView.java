package it.unibo.plantsfarm.view.inventario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.xml.transform.Source;

import it.unibo.plantsfarm.controller.inventario.ControllerInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.gamePanel.ImplViewGamePanel;


public final class UpgradeItemsView extends JDialog {
    private ControllerInventario controllerInventario;
    private final LayoutManager layoutButtonImages = new GridLayout(3,1);
    private final ImplViewGamePanel gamePanel;
    private final JPanel panelIntern;
    private final JPanel buttonImages;
    private final JPanel progressItems;
    private final JPanel buttonsActionsItem;
    private final Map<Tooltype,JProgressBar> progressBarMap = new LinkedHashMap<>();
    private final Map<Tooltype,JButton> progressButtonUpgradeMap = new LinkedHashMap<>();

    public UpgradeItemsView(final ImplViewGamePanel gamePanel) {
        super();
        this.gamePanel = gamePanel;
        this.setResizable(false);
        this.setSize(550, 350);
        this.setLocationRelativeTo(gamePanel);
        this.setModal(true);
        this.panelIntern = new JPanel();
        this.panelIntern.setLayout(new BorderLayout());

        this.buttonImages = new JPanel();
        this.buttonImages.setLayout(layoutButtonImages);

        this.progressItems = new JPanel();
        this.progressItems.setLayout(layoutButtonImages);
        this.buttonsActionsItem = new JPanel();

        createProgressBar();
        createButtonItemsAction();
        createItemButton();
        this.buttonsActionsItem.setLayout(layoutButtonImages);
        this.panelIntern.add(buttonImages,BorderLayout.WEST);
        this.panelIntern.add(buttonsActionsItem,BorderLayout.EAST);
        this.panelIntern.add(progressItems, BorderLayout.CENTER);

        this.add(panelIntern);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent e) {
                restoreFocus();
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                restoreFocus();
            }
        });
    }

    private void restoreFocus() {
        gamePanel.requestFocusInWindow();
        gamePanel.setFocusable(true);
    }

    public void createItemButton(){
        for (Tooltype tool : Tooltype.values()) {
            JButton button = new JButton(" UPGRADE ");
            button.setText(tool.name());
            button.setEnabled(false);
            buttonImages.add(button);
        }
    }

    public void createButtonItemsAction(){

        for (Tooltype tool : Tooltype.values()) {
            JButton upgrade = new JButton(" UPGRADE ");
            upgrade.addActionListener(e -> {
                JButton jb = (JButton) e.getSource();
                if(controllerInventario.isUpgredable(tool)){
                    controllerInventario.pressUpgradeItem(tool);
                }
            });
            progressButtonUpgradeMap.put(tool, upgrade);
            buttonsActionsItem.add(upgrade);
        }
    }

    public void createProgressBar(){
        for (Tooltype tool : Tooltype.values()) {
            JProgressBar progressBar = new JProgressBar();
            this.progressBarMap.put(tool, progressBar);
            this.progressItems.add(progressBar);
        }
    }

    public void setControllerInventory(ControllerInventario controllerInventario){
        this.controllerInventario = controllerInventario;
    }

    public void update () {
        if (controllerInventario == null) {
            return;
        }

        for (Tooltype tool : Tooltype.values()) {
            JProgressBar bar = progressBarMap.get(tool);
            bar.setBackground(new Color(30, 30, 30));
            bar.setForeground(new Color(90, 200, 120));
            bar.setOpaque(true);
            bar.setFont(new Font("Monospaced", Font.BOLD, 12));
            ItemsFarm item = controllerInventario.getInventoryClone().get(tool);
            bar.setMinimum(0);
            bar.setMaximum(item.getExperienceForLevel());
            bar.setValue(item.getExperience());
        }

        for (Tooltype tool : Tooltype.values()) {
            JButton button = progressButtonUpgradeMap.get(tool);
            if(controllerInventario.isUpgredable(tool)){
                button.setEnabled(true);
            }else{
                button.setEnabled(false);
            }
        }
    }

}
