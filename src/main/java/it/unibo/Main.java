package it.unibo;


import javax.swing.JFrame;
import it.unibo.GamePanel.ImplControllerGamePanel;

public class Main {
    
 public static void main(String[] args) {
   
    JFrame frame =  new JFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700,700);
    frame.setVisible(true);

    ImplControllerGamePanel controll = new ImplControllerGamePanel();
    frame.add(controll.getView());
   controll.start();
 }
}
