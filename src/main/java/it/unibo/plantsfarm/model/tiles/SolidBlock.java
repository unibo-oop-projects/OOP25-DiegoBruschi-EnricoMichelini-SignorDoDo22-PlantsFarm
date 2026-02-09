package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;

public final class SolidBlock {
    private Rectangle blockCoordinates;

    public SolidBlock(final Rectangle blockCoordinates) {
        this.blockCoordinates = blockCoordinates;
    }

    public Rectangle getCoordinate() {
        return this.blockCoordinates;
    }
}
