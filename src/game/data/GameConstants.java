package game.data;

import game.Vector2d;
import game.Vector2i;
import game.entity.Collider;


public class GameConstants {
    private final int targetUPS;

    private final int scale;

    private final Vector2d gameScreenSizePercentage;
    private final Vector2d titleScreenSizePercentage;

    //Textures
    private final String[] texturesToLoad;

    private final Collider playerCollider;


    public GameConstants() {
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
}
