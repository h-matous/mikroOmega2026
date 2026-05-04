package game;

import java.awt.image.BufferedImage;

public class Texture {
    private BufferedImage image;

    public Texture(BufferedImage image) {
        this.image = image;
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
