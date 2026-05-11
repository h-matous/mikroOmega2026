package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Objects;

public class TextureManager {
    private static Texture DEFAULT_TEXTURE;

    private static void initDefaultTexture() {
        if (DEFAULT_TEXTURE == null) {
            final int width = 2;
            final int height = 2;

            DEFAULT_TEXTURE = new Texture(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));

            Graphics gfx = DEFAULT_TEXTURE.getImage().getGraphics();

            gfx.setColor(Color.BLACK);
            gfx.fillRect(0, 0, width, height);

            gfx.setColor(Color.MAGENTA);
            gfx.fillRect(width / 2, 0, width / 2, height / 2);
            gfx.fillRect(0, height / 2, width / 2, height / 2);

            gfx.dispose();
        }
    }


    private HashMap<String, Texture> textureMap;


    public TextureManager() {
        initDefaultTexture();

        ArrayList<String> texturesToLoad = new ArrayList<>();

        texturesToLoad.add("assets/banana-1.png");
        texturesToLoad.add("assets/banana-2.png");
        texturesToLoad.add("assets/banana-3.png");
        texturesToLoad.add("assets/banana-4.png");
        texturesToLoad.add("assets/banana-5.png");

        texturesToLoad.add("assets/monkey-idle.png");
        texturesToLoad.add("assets/monkey-walk-left.png");
        texturesToLoad.add("assets/monkey-walk-right.png");


        textureMap = new HashMap<>();

        for (String filePath : texturesToLoad) {
            String[] pathHierarchy = filePath.split("/");
            String key = pathHierarchy[pathHierarchy.length - 1].split("\\.")[0];

            textureMap.put(key, loadTextureFromResource(filePath));
        }

        //textureMap.forEach((key, value)->System.out.println(key + ": " + value));
    }

    public Texture getTexture(String key) {
        return textureMap.get(key);
    }


    private Texture loadTextureFromResource(String filePath) {
        try {
            return new Texture(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + filePath))));
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Failed to load resource: " + e.getMessage());
        }

        return DEFAULT_TEXTURE;
    }

    private BufferedImage loadBufferedImageFromResource(String filePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + filePath)));
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Failed to load resource: " + e.getMessage());
        }

        return DEFAULT_TEXTURE.getImage();
    }
}
