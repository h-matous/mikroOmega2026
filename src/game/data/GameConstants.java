package game.data;

import game.utilities.Direction;
import game.utilities.Vector2d;
import game.utilities.Vector2i;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;


public class GameConstants {
    private int targetUPS;

    private float scaleCoefficient;

    private Vector2d gameScreenSizePercentage;
    private Vector2d titleScreenSizePercentage;

    //Textures
    private List<String> texturesToLoad;

    //Colliders
    private HashMap<String, Collider> colliderMap;

    //Scores
    private HashMap<String, Integer> collectableScoreMap;

    //Background
    private AnimatedBackgroundData animatedBackgroundData;


    //Velocities
    private int initialPlayerWalkingVel;
    private int initialCollectableFallingVel;

    private int maxCollectableRotationSpeed;


    private float playerPercentOfGameScreenHeightAboveGround;

    //Animation direction mapping
    private EnumMap<Direction, PlayerAnimationData> playerAnimationDataMap;

    private Direction playerInitialDirection;

    //Spawning data for collectables
    private CollectableSpawningData collectableSpawningData;


    //When rotation is disabled, it may yield better performance
    private boolean disableEntityRotation;

    //Shows Entity bounds for debugging purposes
    private boolean showEntityBounds;



    public GameConstants() {
        loadDefaults();
    }

    public void loadDefaults() {
        //Target Updates Per Second
        this.targetUPS = 100;

        //Rendering scale, from my testing on FHD 16:9 (1080 / 5 = 216)
        this.scaleCoefficient = 216.0f;

        //Fraction of the users screen resolution
        this.gameScreenSizePercentage = new Vector2d(0.5, 0.8);
        this.titleScreenSizePercentage = new Vector2d(0.4, 0.6);


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
        colliderMap.put("player", new Collider(new Vector2i(20, 26), new Vector2i(24, 38)));

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

        //Velocities
        this.initialPlayerWalkingVel = 8;
        this.initialCollectableFallingVel = 5;

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

        this.playerInitialDirection = Direction.IDLE;

        this.collectableSpawningData = new CollectableSpawningData();

        this.disableEntityRotation = false;
        this.showEntityBounds = false;
    }

    public int getTargetUPS() {
        return targetUPS;
    }

    public float getScaleCoefficient() {
        return scaleCoefficient;
    }

    public Vector2d getGameScreenSizePercentage() {
        return gameScreenSizePercentage;
    }

    public Vector2d getTitleScreenSizePercentage() {
        return titleScreenSizePercentage;
    }


    public List<String> getTexturesToLoad() {
        return texturesToLoad;
    }

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

    public AnimatedBackgroundData getAnimatedBackgroundData() {
        return animatedBackgroundData;
    }

    public int getInitialPlayerWalkingVel() {
        return initialPlayerWalkingVel;
    }

    public int getInitialCollectableFallingVel() {
        return initialCollectableFallingVel;
    }

    public int getMaxCollectableRotationSpeed() {
        return maxCollectableRotationSpeed;
    }


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
     * Used for checking if the rotation of Entities is disabled (only for rendering), disabling it could yield in better performance
     * @return returns the boolean if Entity rotation is disabled
     */
    public boolean isEntityRotationDisabled() {
        return disableEntityRotation;
    }

    /**
     * Used for checking if Entity bounds should be shown, useful when debugging
     * @return returns the boolean if Entity bounds should be shown
     */
    public boolean shouldShowEntityBounds() {
        return showEntityBounds;
    }
}