package it.unibo.plantsfarm.model;

import java.awt.Rectangle;

public class SolidBlock {
    private Rectangle blockCoordinates;

    public SolidBlock(final Rectangle blockCoordinates) {
        this.blockCoordinates = blockCoordinates;
    }

    public Rectangle getCoordinate() {
        return this.blockCoordinates;
    }
}
