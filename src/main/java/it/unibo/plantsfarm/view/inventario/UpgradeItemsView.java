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
    private final LayoutManager layoutInventario = new GridLayout(3,2);
    private final LayoutManager layoutButtonImages = new GridLayout(3,1);
    private final ImplViewGamePanel gamePanel;
    private final JPanel panelIntern;
    private final JPanel buttonImages;
    private final JPanel progressItems;
    private final JPanel buttonsActionsItem;
    private final Map<Tooltype,JProgressBar> progressBarMap = new LinkedHashMap<>();
    private final Map<JButton,Tooltype> progressButtonRepairMap = new LinkedHashMap<>();
    private final Map<JButton,Tooltype> progressButtonUpgradeMap = new LinkedHashMap<>();

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
        this.buttonsActionsItem.setLayout(layoutInventario);
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

    public void createButtonItemsAction(){
        for (Tooltype tool : Tooltype.values()) {
            JButton upgrade = new JButton("+");
            JButton repair = new JButton("Repair");
            repair.addActionListener(e -> {
            JButton jb = (JButton) e.getSource(); // è repair
            Tooltype toolButton = progressButtonRepairMap.get(jb);

            ItemsFarm item = controllerInventario.getInventoryClone().get(toolButton);
            controllerInventario.isReparable(item.getCostRepair(), item.getTooltype());

            update(); // aggiorna barre e bottoni dopo l'azione
            });

            this.buttonsActionsItem.add(upgrade);
            this.buttonsActionsItem.add(repair);
            this.progressButtonRepairMap.put(repair, tool);
            this.progressButtonUpgradeMap.put(upgrade, tool);
        }
    }

    public void createProgressBar(){

        for (Tooltype tool : Tooltype.values()) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setBackground(Color.RED);
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
            bar.setMinimum(0);
            bar.setMaximum(100);
            bar.setValue(controllerInventario.getInventoryClone().get(tool).getIntegrity());
        }

        for (JButton jb : progressButtonRepairMap.keySet()) {
            Tooltype tool = progressButtonRepairMap.get(jb);
            ItemsFarm item = controllerInventario.getInventoryClone().get(tool);
            if(controllerInventario.controllWallet(item.getCostRepair())) {
                jb.setEnabled(true);
            }else{
                jb.setEnabled(false);
            }

            if(item.getLevel() < 20){
                jb.setEnabled(true);
            }else{
                jb.setEnabled(false);
            }
        }
    }

}
