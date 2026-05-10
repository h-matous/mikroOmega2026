package game.background;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LinearVerticalGradientImage extends BufferedImage {
    public LinearVerticalGradientImage(Dimension size, Color topColor, Color bottomColor) {
        super(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        generate(topColor, bottomColor);
    }

    public LinearVerticalGradientImage(Dimension size) {
        super(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
    }

    public void generate(Color topColor, Color bottomColor) {
        Graphics2D gfx = (Graphics2D) this.getGraphics();

        int width = this.getWidth();
        int height = this.getHeight();

        GradientPaint gradient = new GradientPaint(0, 0, topColor, 0, height, bottomColor);

        gfx.setPaint(gradient);

        gfx.fillRect(0, 0, width, height);

        gfx.dispose();
    }
}