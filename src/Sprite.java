import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private Vector2i position;

    private Vector2i size;

    private static BufferedImage DEFAULT_TEXTURE;

    public static void initDefaultTexture() {
        if (DEFAULT_TEXTURE == null) {
            final int width = 2;
            final int height = 2;

            DEFAULT_TEXTURE = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics gfx = DEFAULT_TEXTURE.getGraphics();

            gfx.setColor(Color.BLACK);
            gfx.fillRect(0, 0, width, height);

            gfx.setColor(Color.MAGENTA);
            gfx.fillRect(width / 2, 0, width / 2, height / 2);
            gfx.fillRect(0, height / 2, width / 2, height / 2);

            gfx.dispose();
        }
    }


    private BufferedImage texture;

    public Sprite() {
        initDefaultTexture();

        position = new Vector2i(100, 250);
        size = new Vector2i(200, 200);

        texture = DEFAULT_TEXTURE;
    }

    public Sprite(BufferedImage texture) {
        initDefaultTexture();

        this.texture = texture;

        position = new Vector2i(100, 250);
        size = new Vector2i(200, 200);

        if (texture == null) {
            this.texture = DEFAULT_TEXTURE;
        }
    }


    public void draw(Graphics2D gfx) {
        gfx.drawImage(texture, position.getX(), position.getY(), size.getX(), size.getY(), null);
    }
}
