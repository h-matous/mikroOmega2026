package game.texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Texture class is a wrapper over BufferedImage with simplified useful functionality
 */
public class Texture {
    private final BufferedImage image;
    private boolean isDefault;

    /**
     * Constructor sets the values
     * @param texture the Texture the new Texture is created from
     */
    public Texture(Texture texture) {
        this.image = texture.getImage();
        this.isDefault = false;
    }

    /**
     * Constructor sets the values
     * @param image the BufferedImage the new Texture is created from
     */
    public Texture(BufferedImage image) {
        this.image = image;
        this.isDefault = false;
    }

    /**
     * Used for getting the rotated Texture instance without modifying the original loaded Texture (BufferedImage)
     * @param angleDegrees amount of degrees to rotate by
     * @return returns a new Texture with a rotated appearance
     */
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

    /**
     * Used for getting the horizontally flipped/mirrored instance without modifying the original loaded Texture (BufferedImage)
     * @return returns a new Texture with a mirrored appearance
     */
    public Texture getHorizontallyMirroredInstance() {
        BufferedImage mirroredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        Graphics2D gfx = (Graphics2D) mirroredImage.getGraphics();

        gfx.scale(-1, 1);
        gfx.drawImage(image, null, -mirroredImage.getWidth(), 0);

        gfx.dispose();

        return new Texture(mirroredImage);
    }

    /**
     * Used for getting the vertically flipped/mirrored instance without modifying the original loaded Texture (BufferedImage)
     * @return returns a new Texture with a mirrored appearance
     */
    public Texture getVerticallyMirroredInstance() {
        BufferedImage mirroredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        Graphics2D gfx = (Graphics2D) mirroredImage.getGraphics();

        gfx.scale(1, -1);
        gfx.drawImage(image, null, 0, -mirroredImage.getHeight());

        gfx.dispose();

        return new Texture(mirroredImage);
    }

    /**
     * Used to get a new sub Texture instance without cropping/modifying the original loaded Texture (BufferedImage)
     * @param x starting pos x
     * @param y starting pos y
     * @param width ending pos x
     * @param height ending pos y
     * @return returns a sum image/Texture of the still existing original
     */
    public Texture getSubTexture(int x, int y, int width, int height) {
        return new Texture(image.getSubimage(x, y, width, height));
    }

    /**
     * Used for getting the width of the Texture
     * @return returns the width as an int
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Used for getting the height of the Texture
     * @return returns the height as an int
     */
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Used for getting the BufferedImage core that can be drawn
     * @return returns the core Image as a BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Used for checking if the Texture loading fails in the TextureManager, the Texture is set to be a default Texture instead
     * @return returns if the Texture is default as a boolean
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Used for setting if this Texture is default
     * @param isDefault the new value as a boolean
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}