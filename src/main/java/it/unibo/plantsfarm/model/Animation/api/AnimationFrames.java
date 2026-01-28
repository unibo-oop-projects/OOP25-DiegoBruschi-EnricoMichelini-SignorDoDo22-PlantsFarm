package it.unibo.plantsfarm.model.animation.api;

import java.awt.image.BufferedImage;

import it.unibo.plantsfarm.view.utility.SpriteLoader;

public final class AnimationFrames {

    public final static BufferedImage BASE =  new SpriteLoader("/Player/tile001.png").getImage();
    
    public final static BufferedImage[] WALKRIGHT = new BufferedImage[] { 
        new SpriteLoader("/Player/tile024.png").getImage(),
        new SpriteLoader("/Player/tile025.png").getImage(),
        new SpriteLoader("/Player/tile026.png").getImage(),
        new SpriteLoader("/Player/tile027.png").getImage(),
        new SpriteLoader("/Player/tile028.png").getImage(),
        new SpriteLoader("/Player/tile029.png").getImage()};

    public static final BufferedImage[] WALKLEFT = new BufferedImage[] {
        new SpriteLoader("/Player/Walking/Left/tile024Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile025Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile026Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile027Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile028Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile029Left.png").getImage(),
    };

    public static final BufferedImage[] WALKDOWN = new BufferedImage[] {
        new SpriteLoader("/Player/Walking/Down/tile018.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile019.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile020.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile021.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile022.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile023.png").getImage(),
    };

    public static final BufferedImage[] WALKUP = new BufferedImage[]{
        new SpriteLoader("/Player/Walking/Up/tile030.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile031.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile032.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile033.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile034.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile035.png").getImage(),
    };

    public static  final BufferedImage[] WATER = new BufferedImage[]{
        new SpriteLoader("/Player/Actions/to Water/tile023.png").getImage(),
        new SpriteLoader("/Player/Actions/to Water/tile024.png").getImage(),
        new SpriteLoader("/Player/Actions/to Water/tile023.png").getImage(),
        BASE
    };

    public static final BufferedImage[] DIG = new BufferedImage[]{
        new SpriteLoader("/Player/Actions/Dig/tile012.png").getImage(),
        new SpriteLoader("/Player/Actions/Dig/tile013.png").getImage(),
        new SpriteLoader("/Player/Actions/Dig/tile012.png").getImage(),
        BASE
    };
}
