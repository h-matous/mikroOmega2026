package game.texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Acts as a linear vertical gradient Texture, used for AnimatedBackground
 */
public class LinearVerticalGradientTexture extends Texture {
    public LinearVerticalGradientTexture(Dimension size, Color topColor, Color bottomColor) {
        super(new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB));
        generate(topColor, bottomColor);
    }

    public LinearVerticalGradientTexture(Dimension size) {
        super(new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB));
    }

    public void generate(Color topColor, Color bottomColor) {
        Graphics2D gfx = (Graphics2D) this.getImage().getGraphics();

        int width = this.getWidth();
        int height = this.getHeight();

        GradientPaint gradient = new GradientPaint(0, 0, topColor, 0, height, bottomColor);

        gfx.setPaint(gradient);
        gfx.fillRect(0, 0, width, height);

        gfx.dispose();
    }
}