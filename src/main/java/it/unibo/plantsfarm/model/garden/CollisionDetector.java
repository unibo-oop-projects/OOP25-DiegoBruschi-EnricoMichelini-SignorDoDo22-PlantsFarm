package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;

import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;

public class CollisionDetector {

    private AbstractPlayer player;
    private TileMap map = new TileMap();

    public CollisionDetector(AbstractPlayer player) {
        this.map.loadMap("/maps/map.txt");
        this.player = player;
    }

    public boolean collisionDetection() {

    double offsetX = player.getNextPosX() - player.getPosx();
    double offsetY = player.getNextPosY() - player.getPosy();

    Rectangle currentHitbox = player.getHitBox();

    Rectangle futureHitbox = new Rectangle(
        (int) (currentHitbox.x + offsetX),
        (int) (currentHitbox.y + offsetY),
        currentHitbox.width,
        currentHitbox.height
    );

    for (final SolidBlock tile : map.getSolidBlocks()) {
        if (tile.getCoordinate().intersects(futureHitbox)) {
            return true;
        }
    }
    return false;
}
}

