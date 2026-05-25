package game.texture;

import game.data.GameConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class TextureManager {
    private static Texture DEFAULT_TEXTURE;

    private static void initDefaultTexture() {
        if (DEFAULT_TEXTURE == null) {
            final int width = 64;
            final int height = 64;

            DEFAULT_TEXTURE = new Texture(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
            DEFAULT_TEXTURE.setDefault(true);

            Graphics2D gfx = DEFAULT_TEXTURE.getImage().createGraphics();

            gfx.setColor(Color.BLACK);
            gfx.fillRect(0, 0, width, height);

            gfx.setColor(Color.MAGENTA);
            gfx.fillRect(width / 2, 0, width / 2, height / 2);
            gfx.fillRect(0, height / 2, width / 2, height / 2);

            gfx.dispose();
        }
    }


    private final HashMap<String, Texture> textureMap;


    public TextureManager(GameConstants gameConstants) {
        initDefaultTexture();

        textureMap = new HashMap<>();

        List<String> texturesToLoad = gameConstants.getTexturesToLoad();

        //Iterating over all Texture paths
        for (String filePath : texturesToLoad) {
            String[] pathHierarchy = filePath.split("/");
            String key = pathHierarchy[pathHierarchy.length - 1].split("\\.")[0];

            //Forward slash is expected to be as the first char when loading from resources, if not present trying to load as an external file
            if (filePath.charAt(0) == '/') {
                textureMap.put(key, loadTextureFromResource(filePath));
            }
            else {
                textureMap.put(key, loadTextureFromFile(filePath));
            }
        }
    }

    public Texture getTexture(String key) {
        Texture texture = textureMap.get(key);

        if (texture != null) {
            return texture;
        }

        throw new IllegalArgumentException("Texture name doesn't exist: " + key);
    }

    private Texture loadTexture(InputStream input) throws IOException {
        BufferedImage image = ImageIO.read(input);

        if (image == null) {
            throw new IOException("Failed to load Texture: image format probably unsupported!");
        }

        return new Texture(image);
    }

    private Texture loadTextureFromResource(String resourcePath) {
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.out.println("Resource was not found: " + resourcePath);
                return DEFAULT_TEXTURE;
            }

            return loadTexture(is);
        }
        catch (IOException e) {
            System.out.println("Failed to load resource: " + resourcePath + " - " + e.getMessage());
        }

        return DEFAULT_TEXTURE;
    }

    private Texture loadTextureFromFile(String filePath) {
        Path path = Path.of(filePath);

        try (InputStream is = Files.newInputStream(path)) {
            return loadTexture(is);
        }
        catch (IOException e) {
            System.out.println("Failed to load file: " + filePath + " - " + e.getMessage());
            return DEFAULT_TEXTURE;
        }

    }
}