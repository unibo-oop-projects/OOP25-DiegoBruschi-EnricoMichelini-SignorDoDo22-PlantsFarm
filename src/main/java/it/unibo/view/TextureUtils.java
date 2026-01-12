package it.unibo.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import javax.swing.ImageIcon;

public class TextureUtils {

    public static ImageIcon setLockedIcon(ImageIcon coloredIcon) {
        
        if (coloredIcon == null) {
            return null;
        }

        Image icon = coloredIcon.getImage();
        BufferedImage lockedIcon = new BufferedImage(
            icon.getWidth(null),
            icon.getHeight(null),
            BufferedImage.TYPE_INT_ARGB);

        lockedIcon.getGraphics().drawImage(icon, 0, 0, null);
        RescaleOp op = new RescaleOp(0.3f, 0, null);
        lockedIcon = op.filter(lockedIcon, null);

        return new ImageIcon(lockedIcon);
    }
}
