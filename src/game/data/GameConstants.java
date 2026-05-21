package game.data;

import game.utilities.Vector2d;
import game.utilities.Vector2i;
import game.background.AnimatedBackgroundData;
import game.entity.Collider;


public class GameConstants {
    private int targetUPS;

    private int scale;

    private Vector2d gameScreenSizePercentage;
    private Vector2d titleScreenSizePercentage;

    //Textures
    private String[] texturesToLoad;

    private Collider playerCollider;



    //Background
    private AnimatedBackgroundData animatedBackgroundData;


    public GameConstants() {
        defaultInstance();
    }

    public void defaultInstance() {
        //Target Updates Per Second
        this.targetUPS = 100;

        //Rendering scale
        this.scale = 5;

        //Fraction of the users screen resolution
        this.gameScreenSizePercentage = new Vector2d(0.5, 0.8);
        this.titleScreenSizePercentage = new Vector2d(0.4, 0.6);


        //Textures

        //When loading from reasources, forward slash is at the beginning of the path
        this.texturesToLoad = new String[] {"/assets/banana-1.png", "/assets/banana-2.png", "/assets/banana-3.png", "/assets/banana-4.png", "/assets/banana-5.png", "/assets/monkey-idle.png", "/assets/monkey-walk-left.png", "/assets/monkey-walk-right.png"};

        //Colliders
        //Player Collider
        this.playerCollider = new Collider(new Vector2i(20, 26), new Vector2i(24, 38));

        //Banana Colliders
        //TODO: Colliders for bananas ↑


        //Background
        this.animatedBackgroundData = new AnimatedBackgroundData();
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


    public String[] getTexturesToLoad() {
        return texturesToLoad;
    }

    public Collider getPlayerCollider() {
        return playerCollider;
    }


    public AnimatedBackgroundData getAnimatedBackgroundData() {
        return animatedBackgroundData;
    }
}
