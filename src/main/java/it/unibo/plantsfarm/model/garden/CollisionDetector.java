package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;

import it.unibo.plantsfarm.model.player.api.AbstractPlayer;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;

public final class CollisionDetector {

    private final AbstractPlayer player;
    private final TileMap map = new TileMap();

    public CollisionDetector(final AbstractPlayer player) {
        this.map.loadMap("/maps/map.txt");
        this.player = player;
    }

    public void collisionDetection() {

        final double offsetX = player.getNextPosX() - player.getPosx();
        final double offsetY = player.getNextPosY() - player.getPosy();

        final Rectangle currentHitbox = player.getHitBox();

        final Rectangle futureHitbox = new Rectangle(
            (int) (currentHitbox.x + offsetX),
            (int) (currentHitbox.y + offsetY),
            currentHitbox.width,
            currentHitbox.height
        );

        for (final SolidBlock tile : map.getSolidBlocks()) {
            if (tile.getCoordinate().intersects(futureHitbox)) {
                return;
            }
        }
        player.applyMovement();
    }
}
