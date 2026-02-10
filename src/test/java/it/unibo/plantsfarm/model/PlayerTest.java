package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;
import it.unibo.plantsfarm.model.player.api.*;
import it.unibo.plantsfarm.controller.player.ImplActionHandler;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
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
        AbstractPlayer playerExpert = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        assertTrue(playerExpert instanceof ExpertFarmer);
    }


    @Test
    void testMovementRight() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        ActionHandler action = new ImplActionHandler(player);
        double startX = player.getPosx();
        action.updateDirection(RIGHT);

        player.updatePlayer(2000); // delta simulato
        player.applyMovement();

        double endX = player.getPosx();
        assertTrue(endX > startX,
        "Expected player to move right, startX=" + startX + ", endX=" + endX);
}


    @Test
    void testMovementLeft() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        ActionHandler action = new ImplActionHandler(player);
        action.updateDirection(LEFT);
        double startX = player.getPosx();

        long t0 = 0;
        long t1 = 1000;

        player.updatePlayer(t0);
        player.updatePlayer(t1);
        player.applyMovement();

        double endX = player.getPosx();

        assertTrue(endX < startX);
    }

    @Test
    void testMovementUp() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        ActionHandler action = new ImplActionHandler(player);
        action.updateDirection(UP);
        double startY = player.getPosy();

        long t0 = 0;
        long t1 = 1000;

        player.updatePlayer(t0);
        player.updatePlayer(t1);
        player.applyMovement();

        double endY = player.getPosy();

        assertTrue(endY < startY);
    }

    @Test
    void testMovementDown() {
        AbstractPlayer player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        ActionHandler action = new ImplActionHandler(player);
        action.updateDirection(DOWN);
        double startY = player.getPosy();
        long t0 = 0;
        long t1 = 1000;
        player.updatePlayer(t0);
        player.updatePlayer(t1);
        player.applyMovement();

        double endY = player.getPosy();

        assertTrue(startY < endY);
    }

}
