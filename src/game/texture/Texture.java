package game.texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Texture {
    private final BufferedImage image;
    private boolean isDefault;

    public Texture(Texture texture) {
        this.image = texture.getImage();
        this.isDefault = false;
    }

    public Texture(BufferedImage image) {
        this.image = image;
        this.isDefault = false;
    }

    public Texture getRotatedInstance(double angleDegrees) {
        if (angleDegrees == 0) {
            return new Texture(image);
        }

        BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        Graphics2D gfx = (Graphics2D) rotatedImage.getGraphics();

        gfx.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        gfx.rotate(Math.toRadians(angleDegrees), rotatedImage.getWidth() / 2.0, rotatedImage.getHeight() / 2.0);
        gfx.drawImage(image, null, 0, 0);

        gfx.dispose();

        return new Texture(rotatedImage);
    }

    public Texture getHorizontallyMirroredInstance() {
        BufferedImage mirroredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        Graphics2D gfx = (Graphics2D) mirroredImage.getGraphics();

        gfx.scale(-1, 1);
        gfx.drawImage(image, null, -mirroredImage.getWidth(), 0);

        gfx.dispose();

        return new Texture(mirroredImage);
    }

    public Texture getVerticallyMirroredInstance() {
        BufferedImage mirroredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        Graphics2D gfx = (Graphics2D) mirroredImage.getGraphics();

        gfx.scale(1, -1);
        gfx.drawImage(image, null, 0, -mirroredImage.getHeight());

        gfx.dispose();

        return new Texture(mirroredImage);
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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}