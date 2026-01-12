package it.unibo;


import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.GamePanel.ImplControllerGamePanel;


public class Main {
    
 public static void main(String[] args) {
   
    JFrame frame =  new JFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700,700);
    frame.setVisible(true);

    ImplControllerGamePanel controll = new ImplControllerGamePanel();
    JPanel panel = controll.getView();
    frame.add(panel);
    frame.setFocusable(false);
    panel.requestFocusInWindow();
    controll.start();

   
 }
}
