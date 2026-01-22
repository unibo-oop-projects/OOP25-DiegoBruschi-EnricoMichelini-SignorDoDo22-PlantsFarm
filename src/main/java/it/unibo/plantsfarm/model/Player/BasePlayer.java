package it.unibo.plantsfarm.model.Player;

import java.awt.Rectangle;
import it.unibo.plantsfarm.model.Mappa;
import it.unibo.plantsfarm.model.ModelTile;
import it.unibo.plantsfarm.controller.GamePanel.api.ControllerGamePanel.UserInput;

public final class BasePlayer  {
    private double posX = 0;
    private double posY = 0;
    private double speed = 200;
    private UserInput direction = UserInput.FERMO;

public void updatePlayer(final long time, Mappa mappaModel) {
    
    final double delta = speed * time / 1000.0;
    
    double nextX = posX;
    double nextY = posY;

    switch (direction) {
        case LEFT -> nextX -= delta;
        case RIGHT -> nextX += delta;
        case UP -> nextY -= delta;
        case DOWN -> nextY += delta;
        case FERMO -> { return; }
    }

    Rectangle futureRect = new Rectangle((int)nextX, (int)nextY, 48, 48);

    if (!isColliding(futureRect, mappaModel)) {
        posX = nextX;
        posY = nextY;
    }
}


private boolean isColliding(Rectangle hitBox, Mappa mappa) {
    ModelTile[][] tiles = mappa.getLogicaMap2(); 

    for (int row = 0; row < 21; row++) {
        for (int col = 0; col < 66; col++) {
            ModelTile tile = tiles[row][col];
            
            if (tile != null && !tile.isAttraversabile) {
                if (tile.rect.intersects(hitBox)) {
                    System.out.println("Collisione con tile in posizione: RIGA " + row + " COLONNA " + col);
                    return true; // Collision
                }
            }
        }
    }
    return false; // No collision
}

    public final  void setDirection(final UserInput direction) {
        this.direction = direction;
    }

    public final  double getPosx() { 
        return this.posX; 
    }

    public final double getPosy() { 
        return this.posY; 
    }

    public final UserInput getDirection(){ 
        return this.direction;
    }
}
