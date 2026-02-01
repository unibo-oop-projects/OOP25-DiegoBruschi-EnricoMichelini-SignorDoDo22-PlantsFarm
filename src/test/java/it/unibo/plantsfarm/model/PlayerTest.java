package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;
import it.unibo.plantsfarm.model.player.api.*;
import it.unibo.plantsfarm.model.player.*;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.UP;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    
    FactoryPlayer factory = new ImplFactoryPlayer();

    @Test
    void testCreationPlayer(){
        AbstractPlayer player = factory.createPlayer(PlayersTypes.FARMER);
        assertTrue(player instanceof FarmerPlayer);
    }

    @Test
    void testMovementRight() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        double startX = player.getPosx();
        player.setDirection(RIGHT);
        long t0 = 1000;
        long t1 = 2000;
        player.updatePlayer(t0);
        player.updatePlayer(t1);
        double endX = player.getPosx();
        assertTrue(endX > startX);
    }

    @Test
    void testMovementLeft() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        double startX = player.getPosx();
        player.setDirection(LEFT);
        long t0 = 1000;
        long t1 = 2000;
        player.updatePlayer(t0);
        player.updatePlayer(t1);
        double endX = player.getPosx();
        assertTrue(endX < startX);
    }

    @Test
    void testMovementUp() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        double startY = player.getPosy();
        player.setDirection(UP);
        long t0 = 1000;
        long t1 = 2000;
        player.updatePlayer(t0);
        player.updatePlayer(t1);
        double endY = player.getPosy();
        assertTrue(endY < startY);
    }

    @Test
    void testMovementDown() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        double startY = player.getPosy();
        player.setDirection(DOWN);
        long t0 = 1000;
        long t1 = 2000;
        player.updatePlayer(t0);
        player.updatePlayer(t1);
        double endY = player.getPosy();
        assertTrue(endY > startY);
    }

}
