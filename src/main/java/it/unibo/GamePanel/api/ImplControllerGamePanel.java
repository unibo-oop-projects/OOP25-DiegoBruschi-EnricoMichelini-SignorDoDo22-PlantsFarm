package it.unibo.GamePanel.api;

import ch.qos.logback.core.model.Model;
import it.unibo.GamePanel.ViewGamePanel;

public class ImplControllerGamePanel implements Runnable {

    private ViewGamePanel view;
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;


    public void startLoop(){

    }
    
    @Override
    public void run(){}
    
    public void addView(){
        view = new ViewGamePanel();
        view.setController(this);
    }

    
}
