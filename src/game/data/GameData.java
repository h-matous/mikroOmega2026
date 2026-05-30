package game.data;

import game.Game;
import game.data.statistics.StatLoader;
import game.data.statistics.StatManager;
import game.utilities.input.KeyHandler;
import game.texture.TextureManager;
import game.utilities.Vector2d;
import game.utilities.input.MouseHandler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Random;

/**
 * The class GameData is a superset of the class GameConstants, it contains GameConstants and then some attributes that cannot be known before or may be changed during execution
 */
public class GameData {
    private final Game game;

    private final GameConstants gameConstants;

    private volatile GameState currentGameState;

    private StatManager statManager;

    private final Dimension displayScreenSize;
    private final Vector2d displayScaling;
    private final Dimension scaledDisplayScreenSize;

    //Render scale
    private final float scale;

    private final Dimension gameScreenSize;
    private final Dimension titleScreenSize;
    private final Dimension statScreenSize;

    private final ScoreData playerScoreData;

    private final TextureManager texMngr;

    private final MouseHandler mouseHandler;
    private final KeyHandler keyHandler;

    private InputMethod chosenInputMethod;


    private final Random rnd;

    private final Font labelFont;
    private final Font scoreFont;

    private final int bananaVariationsCount;


    private int currentCollectableFallingSpeed;



    //Setting these to true may yield better performance
    private boolean disableCollectableRotation;
    private boolean disablePlayerAnimation;
    private boolean disableBackgroundAnimation;

    //Shows Entity bounds for debugging purposes
    private boolean showEntityBounds;


    /**
     * Constructor calculates all default values
     * @param game the parent Game instance
     */
    public GameData(Game game) {
        this.game = game;

        this.gameConstants = new GameConstants();

        this.currentGameState = gameConstants.getInitialGameState();

        try {
            //Try to load the Statistics
            this.statManager = StatLoader.loadFromFile(gameConstants.getStatManagerFilePath());
        }
        catch (RuntimeException e) {
            //If it is the first time execution new Statistics file will be created
            this.statManager = StatManager.getFirstTimeExecutionInstance();
            StatLoader.saveToFile(statManager, gameConstants.getStatManagerFilePath());
        }

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        this.displayScreenSize = new Dimension(dm.getWidth(), dm.getHeight());

        AffineTransform at = gd.getDefaultConfiguration().getDefaultTransform();
        this.displayScaling = new Vector2d(at.getScaleX(), at.getScaleY());

        this.scaledDisplayScreenSize = new Dimension((int) (getDisplayScreenSize().width / getDisplayScaling().getX()), (int) (getDisplayScreenSize().height / getDisplayScaling().getY()));


        this.scale = getScaledDisplayScreenSize().height / getConstants().getScaleCoefficient();


        this.gameScreenSize = new Dimension((int) (getScaledDisplayScreenSize().width * getConstants().getGameScreenSizePercentage().getX()), (int) (getScaledDisplayScreenSize().height * getConstants().getGameScreenSizePercentage().getY()));
        this.titleScreenSize = new Dimension((int) (getScaledDisplayScreenSize().width * getConstants().getTitleScreenSizePercentage().getX()), (int) (getScaledDisplayScreenSize().height * getConstants().getTitleScreenSizePercentage().getY()));
        this.statScreenSize = new Dimension((int) (getScaledDisplayScreenSize().width * getConstants().getStatsScreenSizePercentage().getX()), (int) (getScaledDisplayScreenSize().height * getConstants().getStatsScreenSizePercentage().getY()));


        this.playerScoreData = new ScoreData();

        this.texMngr = new TextureManager(gameConstants);

        this.mouseHandler = new MouseHandler();
        this.keyHandler = new KeyHandler();

        this.chosenInputMethod = gameConstants.getInitialInputMethod();

        this.rnd = new Random();

        this.labelFont = new Font("Arial", Font.BOLD, titleScreenSize.width / 30);
        this.scoreFont = new Font("Arial", Font.BOLD, gameScreenSize.width / 12);

        this.bananaVariationsCount = countBananaVariations();


        this.currentCollectableFallingSpeed = gameConstants.getInitialCollectableFallingSpeed();



        this.disableCollectableRotation = false;
        this.disablePlayerAnimation = false;
        this.disableBackgroundAnimation = false;

        this.showEntityBounds = false;
    }

    /**
     * Used for getting the GameConstants
     * @return returns the GameConstants
     */
    public GameConstants getConstants() {
        return gameConstants;
    }

    /**
     * Used for getting the current GameState
     * @return returns the corresponding state as a GameState Enum value
     */
    public GameState getGameState() {
        return currentGameState;
    }

    /**
     * Used for setting the current GameState to a new one
     * @param newGameState the new replacement GameState as a GameState Enum value
     */
    private void setGameState(GameState newGameState) {
        this.currentGameState = newGameState;
    }


    /**
     * Used for changing the current GameState to a new one, also calls revalidateFromGameState() in Game class
     * @param newGameState the new replacement GameState as a GameState Enum value
     */
    public void changeGameState(GameState newGameState) {
        setGameState(newGameState);
        game.revalidateFromGameState(newGameState);
    }

    /**
     * Used for getting the Statistic Manager which holds the Statistic database
     * @return returns the Statistics as a StatManager
     */
    public StatManager getStatManager() {
        return statManager;
    }

    /**
     * Used for getting the real resolution of the display screen
     * @return returns the full screen resolution as a Dimension
     */
    public Dimension getDisplayScreenSize() {
        return displayScreenSize;
    }

    /**
     * Used for getting the operating system scaling for the current display
     * @return returns the scaling as a 2D vector of doubles
     */
    public Vector2d getDisplayScaling() {
        return displayScaling;
    }

    /**
     * Used for getting the scaled display screen size,
     * influenced by operating system scaling
     * @return returns the scaled display screen size as a Dimension
     */
    public Dimension getScaledDisplayScreenSize() {
        return scaledDisplayScreenSize;
    }

    /**
     * Used for getting the calculated rendering scale
     * @return return the calculated render scale as a float
     */
    public float getScale() {
        return scale;
    }

    /**
     * Used for getting the calculated optimal GameScreen window size
     * @return returns the mentioned window size as a Dimension
     */
    public Dimension getGameScreenSize() {
        return gameScreenSize;
    }

    /**
     * Used for getting the calculated optimal TitleScreen window size
     * @return returns the mentioned window size as a Dimension
     */
    public Dimension getTitleScreenSize() {
        return titleScreenSize;
    }

    /**
     * Used for getting the calculated optimal StatScreen window size
     * @return returns the mentioned window size as a Dimension
     */
    public Dimension getStatScreenSize() {
        return statScreenSize;
    }

    /**
     * Used for getting the player's ScoreData
     * @return returns the ScoreData
     */
    public ScoreData getPlayerScoreData() {
        return playerScoreData;
    }

    /**
     * Used for calculating the amount of updates in between each falling collectable Banana spawn
     * @return returns an int representing the amount of GameScene updates
     */
    public int calculateCollectableSpawnTickDelay() {
        CollectableSpawningData data = gameConstants.getCollectableSpawningData();

        return (int) Math.max(data.getBaseSpawnUpdateDelay() - data.getCollectableSpawnCoefficient() * Math.log(playerScoreData.getScore() + 1), data.getMinSpawnUpdateDelay());
    }

    /**
     * Used for getting the TextureManager
     * @return returns the TextureManager
     */
    public TextureManager getTexMngr() {
        return texMngr;
    }

    /**
     * Used for getting the MouseHandler
     * @return returns the MouseHandler
     */
    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    /**
     * Used for getting the KeyHandler
     * @return returns the KeyHandler
     */
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    /**
     * Used for getting the chosen InputMethod
     * @return returns the InputMethod enum value representing the chosen input method
     */
    public InputMethod getChosenInputMethod() {
        return chosenInputMethod;
    }

    /**
     * Used for setting the chosen InputMethod
     * @param chosenInputMethod InputMethod enum value representing the new used input method
     */
    public void setChosenInputMethod(InputMethod chosenInputMethod) {
        this.chosenInputMethod = chosenInputMethod;
    }

    /**
     * Used for getting the Random instance
     * @return returns the random generator as Random
     */
    public Random getRnd() {
        return rnd;
    }

    /**
     * Used for getting the font used for displaying labels
     * @return returns the label font
     */
    public Font getLabelFont() {
        return labelFont;
    }

    /**
     * used for getting the font used for displaying the Score
     * @return returns the Score font
     */
    public Font getScoreFont() {
        return scoreFont;
    }

    /**
     * Used for calculating the amount of collectable falling Banana variations
     * useful when procedurally selecting the Banana variation to spawn
     * @return returns the count of Banana variations as an int
     */
    private int countBananaVariations() {
        return (int) gameConstants.getTexturesToLoad().stream().filter(x->Arrays.stream(x.split("/")).toList().getLast().startsWith("banana")).count();
    }

    /**
     * Used for getting the amount of collectable falling Banana variations
     * useful when procedurally selecting the Banana variation to spawn
     * @return returns the count of Banana variations as an int
     */
    public int getBananaVariationsCount() {
        return bananaVariationsCount;
    }

    /**
     * Used for calculating the current maximum player walking speed, calculates from the falling collectable spawning interval (delay)
     * @return returns the accordingly mapped speed
     */
    public int calculateCurrentPlayerWalkingVel() {
        return Math.max(getConstants().getInitialPlayerWalkingSpeed(), getGameScreenSize().width / calculateCollectableSpawnTickDelay());
    }

    /**
     * Used for getting the current collectable falling speed
     * @return returns an int representing the current collectable falling speed
     */
    public int getCurrentCollectableFallingSpeed() {
        return currentCollectableFallingSpeed;
    }

    /**
     * Used for calculating how many pixels should the player be above ground (bottom of GameScreen)
     * @return returns an int representing the pixel amount
     */
    public int playerPxOffGameScreenGround() {
        return (int) (gameScreenSize.height * gameConstants.getPlayerPercentOfGameScreenHeightAboveGround() * 0.01);
    }


    /**
     * Used for checking if the rotation of falling collectables is disabled, disabling it could yield in better performance
     * @return returns the boolean if collectable rotation is disabled
     */
    public boolean isCollectableRotationDisabled() {
        return disableCollectableRotation;
    }

    /**
     * Used for setting if the rotation of falling collectables is disabled, disabling it could yield in better performance
     * @param disableCollectableRotation the new value as a boolean
     */
    public void setCollectableRotationDisabled(boolean disableCollectableRotation) {
        this.disableCollectableRotation = disableCollectableRotation;
    }

    /**
     * Used for checking if the player animation is disabled, disabling it could yield in better performance
     * @return returns the boolean if player animation is disabled
     */
    public boolean isPlayerAnimationDisabled() {
        return disablePlayerAnimation;
    }

    /**
     * Used for setting if the player animation is disabled, disabling it could yield in better performance
     * @param disablePlayerAnimation the new value as a boolean
     */
    public void setPlayerAnimationDisabled(boolean disablePlayerAnimation) {
        this.disablePlayerAnimation = disablePlayerAnimation;
    }

    /**
     * Used for checking if the background animation is disabled, disabling it could yield in better performance
     * @return returns the boolean if background animation is disabled
     */
    public boolean isBackgroundAnimationDisabled() {
        return disableBackgroundAnimation;
    }

    /**
     * Used for setting if the background animation is disabled, disabling it could yield in better performance
     * @param disableBackgroundAnimation the new value as a boolean
     */
    public void setDisableBackgroundAnimation(boolean disableBackgroundAnimation) {
        this.disableBackgroundAnimation = disableBackgroundAnimation;
    }

    /**
     * Used for checking if Entity bounds should be shown, useful when debugging
     * @return returns the boolean if Entity bounds should be shown
     */
    public boolean shouldShowEntityBounds() {
        return showEntityBounds;
    }

    /**
     * Used for toggling the pause of the gameplay
     */
    public void togglePause() {
        if (currentGameState == GameState.GAME_LOST) {
            return;
        }

        mouseHandler.reset();
        keyHandler.reset();

        switch (currentGameState) {
            case GAME_MAIN:
                changeGameState(GameState.GAME_PAUSE);
                break;
            case GAME_PAUSE:
                changeGameState(GameState.GAME_MAIN);
                break;
        }
    }
}