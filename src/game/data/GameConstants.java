package game.data;

import game.utilities.Vector2d;
import game.utilities.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;


/**
 * The class GameConstants holds information about how the game should behave, feel and look.
 * It stores values that will not be changed during the runtime of the program
 */
public class GameConstants {
    //Game name
    private String gameName;

    //Initial GameState
    private GameState initialGameState;

    //Targeted Updates Per Second
    private int targetUPS;

    private float scaleCoefficient;

    //Screen size coefficients
    private Vector2d gameScreenSizePercentage;
    private Vector2d titleScreenSizePercentage;
    private Vector2d statsScreenSizePercentage;

    //Textures
    private List<String> texturesToLoad;

    //Colliders
    private HashMap<String, Collider> colliderMap;

    //Scores
    private HashMap<String, Integer> collectableScoreMap;

    //Background
    private AnimatedBackgroundData animatedBackgroundData;

    //Initial InputMethod
    private InputMethod initialInputMethod;

    //Velocities
    private int initialPlayerWalkingSpeed;
    private int initialCollectableFallingSpeed;

    private int maxCollectableRotationSpeed;


    private float playerPercentOfGameScreenHeightAboveGround;

    //Animation direction mapping
    private EnumMap<Direction, PlayerAnimationData> playerAnimationDataMap;

    private Direction playerInitialDirection;

    //Spawning data for collectables
    private CollectableSpawningData collectableSpawningData;


    private Color semiTransparentOverlayColor;

    /**
     * Constructor that loads the constants
     */
    public GameConstants() {
        loadDefaults();
    }


    /**
     * Used for loading default constant values for tuned for optimal results
     */
    public void loadDefaults() {
        //Game name
        this.gameName = "Monkey Banana Catch!";

        //Initial GameState
        this.initialGameState = GameState.TITLE_MAIN;

        //Target Updates Per Second
        this.targetUPS = 100;

        //Rendering scale, from my testing on FHD 16:9 (1080 / 5 = 216)
        this.scaleCoefficient = 216.0f;

        //Fraction of the users screen resolution
        this.gameScreenSizePercentage = new Vector2d(0.5, 0.8);
        this.titleScreenSizePercentage = new Vector2d(0.4, 0.6);
        this.statsScreenSizePercentage = new Vector2d(0.4, 0.5);


        //Textures
        this.texturesToLoad = new ArrayList<>();
        texturesToLoad.add("/assets/monkey-idle.png");
        texturesToLoad.add("/assets/monkey-walk-left.png");
        texturesToLoad.add("/assets/monkey-walk-right.png");

        texturesToLoad.add("/assets/banana-1.png");
        texturesToLoad.add("/assets/banana-2.png");
        texturesToLoad.add("/assets/banana-3.png");
        texturesToLoad.add("/assets/banana-4.png");
        texturesToLoad.add("/assets/banana-5.png");

        //When loading Textures from resources, forward slash is at the beginning of the path


        //Colliders
        this.colliderMap = new HashMap<>();
        colliderMap.put("player", new Collider(new Vector2i(20, 26), new Vector2i(24, 13)));

        colliderMap.put("banana-1", new Collider(new Vector2i(26, 24), new Vector2i(13, 15)));
        colliderMap.put("banana-2", new Collider(new Vector2i(27, 24), new Vector2i(10, 14)));
        colliderMap.put("banana-3", new Collider(new Vector2i(25, 23), new Vector2i(15, 16)));
        colliderMap.put("banana-4", new Collider(new Vector2i(25, 23), new Vector2i(13, 15)));
        colliderMap.put("banana-5", new Collider(new Vector2i(25, 23), new Vector2i(14, 16)));

        //CollectableScores
        this.collectableScoreMap = new HashMap<>();
        collectableScoreMap.put("banana-1", 1);
        collectableScoreMap.put("banana-2", 1);
        collectableScoreMap.put("banana-3", 1);
        collectableScoreMap.put("banana-4", 3);
        collectableScoreMap.put("banana-5", 3);

        //Background
        this.animatedBackgroundData = new AnimatedBackgroundData();

        //Initial InputMethod
        this.initialInputMethod = InputMethod.KEYBOARD;

        //Velocities/speeds
        this.initialPlayerWalkingSpeed = 8;
        this.initialCollectableFallingSpeed = 5;

        this.maxCollectableRotationSpeed = 5;


        this.playerPercentOfGameScreenHeightAboveGround = 0.0f;

        //Animation direction mapping
        this.playerAnimationDataMap = new EnumMap<>(Direction.class);
        playerAnimationDataMap.put(Direction.IDLE, new PlayerAnimationData("monkey-idle", 1, 1));
        playerAnimationDataMap.put(Direction.UP, new PlayerAnimationData("monkey-idle", 1, 1));
        playerAnimationDataMap.put(Direction.DOWN, new PlayerAnimationData("monkey-idle", 1, 1));
        playerAnimationDataMap.put(Direction.LEFT, new PlayerAnimationData("monkey-walk-left", 8, 36));
        playerAnimationDataMap.put(Direction.RIGHT, new PlayerAnimationData("monkey-walk-right", 8, 36));

        //When using a still Texture instead, frameCount is expected to be 1

        //Initial Player Direction, if Player Animation is disabled Texture will correspond with this initial Direction
        this.playerInitialDirection = Direction.IDLE;

        //Collectable spawning data
        this.collectableSpawningData = new CollectableSpawningData();

        //Semi-transparent Color for overlays
        this.semiTransparentOverlayColor = new Color(0, 0, 0, 200);
    }

    /**
     * Used for getting the name of the Game
     * @return returns the Game name as a String
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Used for getting the initial GameState
     * @return returns the initial state as a GameState Enum value
     */
    public GameState getInitialGameState() {
        return initialGameState;
    }

    /**
     * Used for getting the target Updates Per Second for fixed-step physics updating
     * @return returns the amount of updates per second as an int
     */
    public int getTargetUPS() {
        return targetUPS;
    }

    /**
     * Used for calculating the rendering scale, smaller value corresponds to bigger rendered scenes and Entities
     * @return return the render scale coefficient as a float
     */
    public float getScaleCoefficient() {
        return scaleCoefficient;
    }

    /**
     * Used for getting the percentage of the user's display the GameScreen window should take up
     * @return returns a percentage as a Vector2D
     */
    public Vector2d getGameScreenSizePercentage() {
        return gameScreenSizePercentage;
    }

    /**
     * Used for getting the percentage of the user's display the TitleScreen window should take up
     * @return returns a percentage as a Vector2D
     */
    public Vector2d getTitleScreenSizePercentage() {
        return titleScreenSizePercentage;
    }

    /**
     * Used for getting the percentage of the user's display the StatScreen window should take up
     * @return returns a percentage as a Vector2D
     */
    public Vector2d getStatsScreenSizePercentage() {
        return statsScreenSizePercentage;
    }


    /**
     * Used for getting all the Texture names to be loaded
     * @return returns a List of Strings corresponding to the Texture file names
     */
    public List<String> getTexturesToLoad() {
        return texturesToLoad;
    }

    /**
     * Used for getting the collider for an Entity
     * @param key an ID represented as a String corresponding to the Entity
     * @return returns a Collider if the key exists
     */
    public Collider getCollider(String key) {
        Collider collider = colliderMap.get(key);

        if (collider != null) {
            return colliderMap.get(key);
        }

        throw new IllegalArgumentException("Collider name doesn't exist: " + key);
    }

    /**
     * Used for getting the score player receives after picking a collectable (Banana)
     *
     * @param key ID (name) of the collectable (Banana)
     * @return returns an int that represents how much score should the player receive after collecting the collectable
     */
    public int getCollectableScore(String key) {
        Integer collectibleScore = collectableScoreMap.get(key);

        if (collectibleScore != null) {
            return collectibleScore;
        }

        throw new IllegalArgumentException("CollectibleScore name doesn't exist: " + key);
    }

    /**
     * Used for getting the AnimatedBackgroundData containing mostly information about the BackgroundDroplet and colors
     * @return returns the AnimatedBackgroundData
     */
    public AnimatedBackgroundData getAnimatedBackgroundData() {
        return animatedBackgroundData;
    }

    /**
     * Used for getting the initial InputMethod to use during the gameplay of the Game
     * @return returns the InputMethod enum value representing the initial input method
     */
    public InputMethod getInitialInputMethod() {
        return initialInputMethod;
    }

    /**
     * Used for getting the initial walking speed of the player
     * @return returns an int representing the initial amount of units the player can move by every player Entity update
     */
    public int getInitialPlayerWalkingSpeed() {
        return initialPlayerWalkingSpeed;
    }

    /**
     * Used for getting the initial collectable falling speed
     * @return returns an int representing the initial amount of units to move the falling collectable Entity by every collectable Entity update
     */
    public int getInitialCollectableFallingSpeed() {
        return initialCollectableFallingSpeed;
    }

    /**
     * Used for getting the maximum rotation speed of collectable Entities
     * @return returns an int representing a maximum amount of degrees to rotate per collectable Entity update
     */
    public int getMaxCollectableRotationSpeed() {
        return maxCollectableRotationSpeed;
    }

    /**
     * Used for getting a coefficient as a part of a calculation which determines how many pixels should the player be above ground (bottom of GameScreen)
     * @return returns an int representing the pixel amount
     */
    public float getPlayerPercentOfGameScreenHeightAboveGround() {
        return playerPercentOfGameScreenHeightAboveGround;
    }

    /**
     * Used for getting the PlayerAnimationData, mapping an Animation for every Direction
     * @param key Direction for which the AnimationData is requested
     * @return returns PlayerAnimationData containing most importantly the texture name
     */
    public PlayerAnimationData getPlayerAnimationData(Direction key) {
        PlayerAnimationData playerAnimationData = playerAnimationDataMap.get(key);

        if (playerAnimationData != null) {
            return playerAnimationData;
        }

        throw new IllegalArgumentException("PlayerAnimationData name doesn't exist: " + key);
    }

    /**
     * Used for getting the entire PlayerAnimationData EnumMap
     * @return returns a EnumMap containing unique PlayerAnimationData for every Direction
     */
    public EnumMap<Direction, PlayerAnimationData> getPlayerAnimationDataMap() {
        return playerAnimationDataMap;
    }

    /**
     * Used for getting the initial Direction state for the Player Animation
     * @return returns the initial Direction
     */
    public Direction getPlayerInitialDirection() {
        return playerInitialDirection;
    }

    /**
     * Used for getting the information about collectable spawning
     * @return returns the collectable spawning data
     */
    public CollectableSpawningData getCollectableSpawningData() {
        return collectableSpawningData;
    }

    /**
     * Used for getting the semi-transparent Color for overlays
     * @return returns the overlay color as a Color
     */
    public Color getSemiTransparentOverlayColor() {
        return semiTransparentOverlayColor;
    }
}