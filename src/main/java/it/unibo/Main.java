package it.unibo;

import javax.swing.JFrame;

import it.unibo.plantsfarm.controller.GamePanel.ImplControllerGamePanel;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World");
        frame.setSize(700, 700);
        frame.setVisible(true);
        ImplControllerGamePanel controller = new ImplControllerGamePanel();
        controller.addView();
        frame.add(controller.getView());
        controller.start();
        controller.getView().requestFocus();        
    }
    
}
