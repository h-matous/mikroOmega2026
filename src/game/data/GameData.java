package game.data;

import game.utilities.KeyHandler;
import game.texture.TextureManager;
import game.utilities.Vector2d;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Random;

/**
 * The class GameData is a superset of the class GameConstants, it contains GameConstants and then some attributes that cannot be known before or may be changed during execution
 */
public class GameData {
    private final GameConstants gameConstants;

    private final Dimension displayScreenSize;
    private final Vector2d displayScaling;
    private final Dimension scaledDisplayScreenSize;

    //Render scale
    private final float scale;

    private final Dimension gameScreenSize;
    private final Dimension titleScreenSize;

    private final ScoreData playerScoreData;

    private final TextureManager texMngr;

    private final KeyHandler keyHandler;


    private final Random rnd;

    private final Font labelFont;
    private final Font scoreFont;

    private final int bananaVariationsCount;


    private int currentPlayerWalkingVel;

    private int currentCollectibleFallingVel;

    public GameData() {
        this.gameConstants = new GameConstants();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        this.displayScreenSize = new Dimension(dm.getWidth(), dm.getHeight());

        AffineTransform at = gd.getDefaultConfiguration().getDefaultTransform();
        this.displayScaling = new Vector2d(at.getScaleX(), at.getScaleY());

        this.scaledDisplayScreenSize = new Dimension((int) (getDisplayScreenSize().width / getDisplayScaling().getX()), (int) (getDisplayScreenSize().height / getDisplayScaling().getY()));


        this.scale = getScaledDisplayScreenSize().height / getConstants().getScaleCoefficient();


        this.gameScreenSize = new Dimension((int) (getScaledDisplayScreenSize().width * getConstants().getGameScreenSizePercentage().getX()), (int) (getScaledDisplayScreenSize().height * getConstants().getGameScreenSizePercentage().getY()));
        this.titleScreenSize = new Dimension((int) (getScaledDisplayScreenSize().width * getConstants().getTitleScreenSizePercentage().getX()), (int) (getScaledDisplayScreenSize().height * getConstants().getTitleScreenSizePercentage().getY()));

        this.playerScoreData = new ScoreData();

        this.texMngr = new TextureManager(gameConstants);

        this.keyHandler = new KeyHandler();

        this.rnd = new Random();

        this.labelFont = new Font("Arial", Font.BOLD, titleScreenSize.width / 30);
        this.scoreFont = new Font("Arial", Font.BOLD, gameScreenSize.width / 12);

        this.bananaVariationsCount = countBananaVariations();


        this.currentPlayerWalkingVel = gameConstants.getInitialPlayerWalkingVel();

        this.currentCollectibleFallingVel = gameConstants.getInitialCollectableFallingVel();
    }

    public GameConstants getConstants() {
        return gameConstants;
    }

    public Dimension getDisplayScreenSize() {
        return displayScreenSize;
    }

    public Vector2d getDisplayScaling() {
        return displayScaling;
    }

    public Dimension getScaledDisplayScreenSize() {
        return scaledDisplayScreenSize;
    }

    public float getScale() {
        return scale;
    }

    public Dimension getGameScreenSize() {
        return gameScreenSize;
    }

    public Dimension getTitleScreenSize() {
        return titleScreenSize;
    }

    public ScoreData getPlayerScoreData() {
        return playerScoreData;
    }

    public int calculateCollectableSpawnTickDelay() {
        CollectableSpawningData data = gameConstants.getCollectableSpawningData();

        return (int) Math.max(data.getBaseSpawnUpdateDelay() - data.getCollectableSpawnCoefficient() * Math.log(playerScoreData.getScore() + 1), data.getMinSpawnUpdateDelay());
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

    public int getCurrentPlayerWalkingVel() {
        return currentPlayerWalkingVel;
    }

    public int getCurrentCollectibleFallingVel() {
        return currentCollectibleFallingVel;
    }

    public int playerPxOffGameScreenGround() {
        return (int) (gameScreenSize.height * gameConstants.getPlayerPercentOfGameScreenHeightAboveGround() * 0.01);
    }
}