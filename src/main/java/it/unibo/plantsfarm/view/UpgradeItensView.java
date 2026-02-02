package it.unibo.plantsfarm.view;

import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class UpgradeItensView extends JDialog {

    private JPanel screenIntern;

    public UpgradeItensView(){
        this.setResizable(false);
        this.setSize(400, 400);

        this.screenIntern = new JPanel();
        screenIntern.setLayout(new GridLayout(3,3,5,5));

    }

}
