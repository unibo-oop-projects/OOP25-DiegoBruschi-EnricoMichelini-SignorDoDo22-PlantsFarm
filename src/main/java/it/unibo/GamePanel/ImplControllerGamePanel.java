package it.unibo.GamePanel;

import static it.unibo.GamePanel.api.ControllerGamePanel.UserInput.AZIONE;

import java.util.concurrent.LinkedBlockingQueue;
import it.unibo.GamePanel.api.ControllerGamePanel.UserInput;
import it.unibo.Player.BasePlayer;

public class ImplControllerGamePanel extends Thread {
    private ViewGamePanel view;
    private static final int SLEEPING_PERIOD_IN_MILLISECONDS = 10;
    private final LinkedBlockingQueue<UserInput> queue = new LinkedBlockingQueue<>();
    private BasePlayer player;

    public ImplControllerGamePanel() {
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
            UserInput input = queue.poll();
            if (input != null) {
                player.setDirection(input);
            }

            if( input == AZIONE ){
                setAnimazione();
                view.azioneDisegna = true;
                
                System.out.print("qua controller \n");
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

    /**
     * 
     * @param input
     */
    public void takeInput(final UserInput input) {
        if(input != null){
        this.queue.add(input);
        }
    }
    
    /**
     * Create the Main GamePanel. Only one GamePanel can be create at time
     */
    public void addView() {
        view = new ViewGamePanel();
        view.setController(this);
    }

    /**
     * 
     * @return
     */
    public ViewGamePanel getView() {
        return this.view;
    }

    public void setPlayer() {
        this.player = new BasePlayer();
    }

    public BasePlayer getPlayer() {
        return this.player;
    }

    public void openInventario(){
        
    }

    public void avviaAnimazione(){
        view.azioneDisenga = true;
    }

    public void setAnimazione(){
        view.azioneStartTime = System.nanoTime();
    }

}
