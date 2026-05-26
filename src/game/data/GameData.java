package game.data;

import game.utilities.KeyHandler;
import game.texture.TextureManager;
import game.utilities.Vector2i;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class GameData {
    private final GameConstants gameConstants;

    private final Dimension displayScreenSize;
    private final Dimension gameScreenSize;
    private final Dimension titleScreenSize;

    private final TextureManager texMngr;

    private final KeyHandler keyHandler;


    private final Random rnd;

    private final Font labelFont;
    private final Font scoreFont;

    private final int bananaVariationsCount;

    private Vector2i currentCollectibleFallingVel;

    public GameData() {
        this.gameConstants = new GameConstants();

        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        this.displayScreenSize = new Dimension(dm.getWidth(), dm.getHeight());

        this.gameScreenSize = new Dimension((int) (displayScreenSize.width * gameConstants.getGameScreenSizePercentage().getX()), (int) (displayScreenSize.height * gameConstants.getGameScreenSizePercentage().getY()));
        this.titleScreenSize = new Dimension((int) (displayScreenSize.width * gameConstants.getTitleScreenSizePercentage().getX()), (int) (displayScreenSize.height * gameConstants.getTitleScreenSizePercentage().getY()));

        this.texMngr = new TextureManager(gameConstants);

        this.keyHandler = new KeyHandler();

        this.rnd = new Random();

        this.labelFont = new Font("Arial", Font.BOLD, titleScreenSize.width / 30);
        this.scoreFont = new Font("Arial", Font.BOLD, gameScreenSize.width / 12);

        this.bananaVariationsCount = countBananaVariations();

        this.currentCollectibleFallingVel = new Vector2i(0, gameConstants.getInitialCollectibleFallingVel());
    }

    public GameConstants getConstants() {
        return gameConstants;
    }

    public Dimension getDisplayScreenSize() {
        return displayScreenSize;
    }

    public Dimension getGameScreenSize() {
        return gameScreenSize;
    }

    public Dimension getTitleScreenSize() {
        return titleScreenSize;
    }

    public TextureManager getTexMngr() {
        return texMngr;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public Random getRnd() {
        return rnd;
    }

    public Font getLabelFont() {
        return labelFont;
    }

    public Font getScoreFont() {
        return scoreFont;
    }

    private int countBananaVariations() {
        return (int) gameConstants.getTexturesToLoad().stream().filter(x->Arrays.stream(x.split("/")).toList().getLast().startsWith("banana")).count();
    }

    public int getBananaVariationsCount() {
        return bananaVariationsCount;
    }

    public Vector2i getCurrentCollectibleFallingVel() {
        return currentCollectibleFallingVel;
    }

    public int playerPxOffGameScreenGround() {
        return (int) (gameScreenSize.height * gameConstants.getPlayerPercentOfGameScreenHeightAboveGround() * 0.01);
    }
}