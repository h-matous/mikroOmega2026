package game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Texture {
    private BufferedImage image;

    public Texture(Texture texture) {
        this.image = texture.getImage();
    }

    public Texture(BufferedImage image) {
        this.image = image;
    }

    public Texture getRotatedInstance(double angleDegrees) {
        BufferedImage rotatedImage = new BufferedImage(image.getWidth(),image.getHeight(), image.getType());

        Graphics2D gfx = (Graphics2D) rotatedImage.getGraphics();

        gfx.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        gfx.rotate(Math.toRadians(angleDegrees), rotatedImage.getWidth() / 2.0, rotatedImage.getHeight() / 2.0);
        gfx.drawImage(image, null, 0, 0);

        gfx.dispose();

        return new Texture(rotatedImage);
    }



    public Texture getSubTexture(int x, int y, int width, int height) {
        return new Texture(image.getSubimage(x, y, width, height));
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public BufferedImage getImage() {
        return image;
    }
}
