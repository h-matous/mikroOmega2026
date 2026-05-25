package game.renderable.entity;

import game.utilities.Animation;
import game.utilities.KeyHandler;
import game.texture.TextureManager;
import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;
import java.util.HashMap;

public class Player extends Entity {

    private enum Direction {
        IDLE(new Vector2i(0, 0)),
        UP(new Vector2i(0, -1)),
        DOWN(new Vector2i(0, 1)),
        LEFT(new Vector2i(-1, 0)),
        RIGHT(new Vector2i(1, 0));

        private final Vector2i dir;

        Direction(Vector2i dir) {
            this.dir = dir;
        }

        public Vector2i getVector() {
            return dir;
        }
    }

    private Direction direction;

    private HashMap<Direction, Animation> animationMap;
    private Animation currentAnimation;


    public Player(GameData gameData) {

        setDefaultValues(gameData);

        initializeAnimationMap(gameData);
    }

    public void setDefaultValues(GameData gameData) {
        this.pos = new Vector2i(100, 100);
        this.scale = gameData.getConstants().getScale();
        this.size = new Vector2i(gameData.getTexMngr().getTexture("monkey-idle").getWidth());
        this.vel = new Vector2i();

        this.collider = gameData.getConstants().getCollider("player");

        direction = Direction.IDLE;

        this.rotation = 0;

        this.colliderEnabled = true;
    }



    public void initializeAnimationMap(GameData gameData) {
        animationMap = new HashMap<>();

        TextureManager texMngr = gameData.getTexMngr();
        int targetUPS = gameData.getConstants().getTargetUPS();


        //TODO: DO something idk
        animationMap.put(Direction.IDLE, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.UP, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.DOWN, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.LEFT, new Animation(texMngr.getTexture("monkey-walk-left"), 8, 18, targetUPS));
        animationMap.put(Direction.RIGHT, new Animation(texMngr.getTexture("monkey-walk-right"), 8, 18, targetUPS));

        currentAnimation = animationMap.get(direction);
    }



    @Override
    public void update(GameData gameData) {
        super.update(gameData);

        direction = Direction.IDLE;
        vel.setBoth(0 ,0);

        KeyHandler keyH = gameData.getKeyHandler();

        if (keyH.isUpPressed()) {
            direction = Direction.UP;
            vel.add(direction.getVector());
        }
        if (keyH.isDownPressed()) {
            direction = Direction.DOWN;
            vel.add(direction.getVector());
        }
        if (keyH.isLeftPressed()) {
            direction = Direction.LEFT;
            vel.add(direction.getVector());
        }
        if (keyH.isRightPressed()) {
            direction = Direction.RIGHT;
            vel.add(direction.getVector());
        }

        if (vel.getX() == 0) direction = Direction.IDLE;

        vel.multiply(gameData.getConstants().getMaxPlayerWalkingVel());

        pos.add(vel);


        currentAnimation = animationMap.get(direction);
        currentAnimation.update();
    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        super.draw(gfx, gameData);

        gfx.drawImage(currentAnimation.getCurrentFrame().getImage(), pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale, null);
    }
}