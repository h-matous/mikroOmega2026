package game.data;

import game.utilities.Vector2d;
import game.utilities.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameConstants {
    private int targetUPS;

    private int scale;

    private Vector2d gameScreenSizePercentage;
    private Vector2d titleScreenSizePercentage;

    //Textures
    private List<String> texturesToLoad;

    //Colliders
    private HashMap<String, Collider> colliderMap;

    //Scores
    private HashMap<String, Integer> collectibleScoreMap;

    //Background
    private AnimatedBackgroundData animatedBackgroundData;


    //Velocities
    private int maxPlayerWalkingVel;
    private int initialCollectibleFallingVel;

    private int maxCollectibleRotationSpeed;


    public GameConstants() {
        loadDefaults();
    }

    public void loadDefaults() {
        //Target Updates Per Second
        this.targetUPS = 100;

        //Rendering scale
        this.scale = 5;

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

        //CollectibleScores
        this.collectibleScoreMap = new HashMap<>();
        collectibleScoreMap.put("banana-1", 1);
        collectibleScoreMap.put("banana-2", 1);
        collectibleScoreMap.put("banana-3", 1);
        collectibleScoreMap.put("banana-4", 3);
        collectibleScoreMap.put("banana-5", 3);

        //Background
        this.animatedBackgroundData = new AnimatedBackgroundData();

        //Velocities
        this.maxPlayerWalkingVel = 4;
        this.initialCollectibleFallingVel = 5;

        this.maxCollectibleRotationSpeed = 5;
    }

    public int getTargetUPS() {
        return targetUPS;
    }

    public int getScale() {
        return scale;
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
     * Used for getting the score player receives after picking a collectible (Banana)
     * @param key ID (name) of the collectible (Banana)
     * @return returns an int that represents how much score should the player receive after collecting the collectible
     */
    public int getCollectibleScore(String key) {
        Integer collectibleScore = collectibleScoreMap.get(key);

        if (collectibleScore != null) {
            return collectibleScore;
        }

        throw new IllegalArgumentException("CollectibleScore name doesn't exist: " + key);
    }

    public AnimatedBackgroundData getAnimatedBackgroundData() {
        return animatedBackgroundData;
    }

    public int getMaxPlayerWalkingVel() {
        return maxPlayerWalkingVel;
    }

    public int getInitialCollectibleFallingVel() {
        return initialCollectibleFallingVel;
    }

    public int getMaxCollectibleRotationSpeed() {
        return maxCollectibleRotationSpeed;
    }
}