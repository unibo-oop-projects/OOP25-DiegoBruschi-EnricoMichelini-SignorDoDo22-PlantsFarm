package it.unibo.GamePanel;
import java.util.concurrent.LinkedBlockingQueue;


import it.unibo.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.Player.BasePlayer;

public class ImplControllerGamePanel extends Thread {

    private ViewGamePanel view;
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private BasePlayer player;

    public ImplControllerGamePanel(){
        this.player = new BasePlayer();
        this.view = new ViewGamePanel();
        this.view.setController(this);
        
    }

  @Override
public void run() {
    
    long lastTime = System.currentTimeMillis();
    
    while (true) {
        long now = System.currentTimeMillis();
        long delta = now - lastTime;
        lastTime = now;
        stampaList();
    
        UserInput input = queue.poll();
        if (input != null) {
            player.setDirection(input);
        }
        
        view.show(player.getPosx(), player.getPosy());

        try {
            Thread.sleep(SLEEPING_PERIOD_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            break;
        }
        player.updatePlayer(delta);
    }
}

    public void takeInput(UserInput input){
        if(input != null){
        this.queue.add(input);
        }
    }
    
    
    public void addView(){
        view = new ViewGamePanel();
        view.setController(this);
    }

    public ViewGamePanel getView(){
        return this.view;
    }

    public void setPlayer(){
        this.player = new BasePlayer();
    }

    public void stampaList(){
        System.out.println("** LISTA **");
        for (UserInput userInput : queue) {
            System.out.println(userInput);
        }
    }

    
}
