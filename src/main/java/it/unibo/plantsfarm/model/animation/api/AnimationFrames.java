package it.unibo.plantsfarm.model.animation.api;

import java.awt.image.BufferedImage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.plantsfarm.view.utility.SpriteLoader;

/**
 * The class AnimationFrames contains the sprite frames used for player animations.
 * This class provides predefined {@link BufferedImage} arrays representing the different animation
 * state of the player, such as walking and actions.
 */
@SuppressFBWarnings(value = "MS_EXPOSE_REP", justification = "BufferedImage is treated as read-only in this context")
public final class AnimationFrames {

    private static final BufferedImage BASE = new SpriteLoader("/Player/tile001.png").getImage();

    private static final BufferedImage[] AXE = {
        new SpriteLoader("/Player/Actions/RemovePlant/axe1.png").getImage(),
        new SpriteLoader("/Player/Actions/RemovePlant/axe2.png").getImage(),
        new SpriteLoader("/Player/Actions/RemovePlant/axe1.png").getImage(),
        BASE,
    };

    private static final BufferedImage[] WALKRIGHT = {
        new SpriteLoader("/Player/tile024.png").getImage(),
        new SpriteLoader("/Player/tile025.png").getImage(),
        new SpriteLoader("/Player/tile026.png").getImage(),
        new SpriteLoader("/Player/tile027.png").getImage(),
        new SpriteLoader("/Player/tile028.png").getImage(),
        new SpriteLoader("/Player/tile029.png").getImage()};

    private static final BufferedImage[] WALKLEFT = {
        new SpriteLoader("/Player/Walking/Left/tile024Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile025Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile026Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile027Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile028Left.png").getImage(),
        new SpriteLoader("/Player/Walking/Left/tile029Left.png").getImage(),
    };

    private static final BufferedImage[] WALKDOWN = {
        new SpriteLoader("/Player/Walking/Down/tile018.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile019.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile020.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile021.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile022.png").getImage(),
        new SpriteLoader("/Player/Walking/Down/tile023.png").getImage(),
    };

    private static final BufferedImage[] WALKUP = {
        new SpriteLoader("/Player/Walking/Up/tile030.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile031.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile032.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile033.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile034.png").getImage(),
        new SpriteLoader("/Player/Walking/Up/tile035.png").getImage(),
    };

    private static final BufferedImage[] WATER = {
        new SpriteLoader("/Player/Actions/to Water/tile023.png").getImage(),
        new SpriteLoader("/Player/Actions/to Water/tile024.png").getImage(),
        new SpriteLoader("/Player/Actions/to Water/tile023.png").getImage(),
        BASE,
    };

    private static final BufferedImage[] DIG = {
        new SpriteLoader("/Player/Actions/Dig/tile012.png").getImage(),
        new SpriteLoader("/Player/Actions/Dig/tile013.png").getImage(),
        new SpriteLoader("/Player/Actions/Dig/tile012.png").getImage(),
        BASE,
    };

    private AnimationFrames() {
    }


    public static BufferedImage base() {
        return BASE;
    }

    public static BufferedImage[] axe() {
        return AXE.clone();
    }

    public static BufferedImage[] walkRight() {
        return WALKRIGHT.clone();
    }

    public static BufferedImage[] walkLeft() {
        return WALKLEFT.clone();
    }

    public static BufferedImage[] walkDown() {
        return WALKDOWN.clone();
    }

    public static BufferedImage[] walkUp() {
        return WALKUP.clone();
    }

    public static BufferedImage[] water() {
        return WATER.clone();
    }

    public static BufferedImage[] dig() {
        return DIG.clone();
    }
}
