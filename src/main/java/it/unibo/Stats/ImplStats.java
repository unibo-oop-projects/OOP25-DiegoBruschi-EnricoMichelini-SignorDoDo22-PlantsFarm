package it.unibo.Stats;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ImplStats extends JPanel {

    private JProgressBar health; 
    private JProgressBar energy;

    public ImplStats(){

        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        health = new JProgressBar();
        health.setBackground(Color.RED);
        energy = new JProgressBar();
        energy.setBackground(Color.GREEN);
        this.add(energy);
        this.add(health);
        

    }


}
