package it.unibo.plantsfarm.view;

import java.awt.image.BufferedImage;

import it.unibo.plantsfarm.view.utility.SpriteLoader;

public class ItemsView {

    public static final BufferedImage HO_IMAGE = new SpriteLoader("/Player/Items/Hoe.png").getImage();
    public static final BufferedImage WATER_CAN_IMAGE = new SpriteLoader("/Player/Items/WaterBucket.png").getImage();
}
