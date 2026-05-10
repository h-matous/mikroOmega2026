package game.entity;

import game.KeyHandler;
import game.TextureManager;
import game.Vector2i;

import java.awt.*;
import java.util.HashMap;

public class Player extends Entity {
    KeyHandler keyH;

    private int maxVel;

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


    public Player(int scale, KeyHandler keyH, TextureManager texMngr, int targetUPS) {
        this.keyH = keyH;

        setDefaultValues(scale, texMngr);

        initializeAnimationMap(texMngr, targetUPS);
    }

    public void setDefaultValues(int scale, TextureManager texMngr) {
        this.pos = new Vector2i(100, 100);
        this.scale = scale;
        this.size = new Vector2i(texMngr.getTexture("monkey-idle").getWidth());
        this.vel = new Vector2i();

        this.collider = new Collider(new Vector2i(20, 26), new Vector2i(24, 38));

        direction = Direction.IDLE;

        this.maxVel = 4;
    }



    public void initializeAnimationMap(TextureManager texMngr, int targetUPS) {
        animationMap = new HashMap<>();

        animationMap.put(Direction.IDLE, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.UP, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.DOWN, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.LEFT, new Animation(texMngr.getTexture("monkey-walk-left"), 8, 18, targetUPS));
        animationMap.put(Direction.RIGHT, new Animation(texMngr.getTexture("monkey-walk-right"), 8, 18, targetUPS));

        currentAnimation = animationMap.get(direction);
    }

    public void update() {
        direction = Direction.IDLE;
        vel.setBoth(0 ,0);

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

        vel.multiply(maxVel);

        pos.add(vel);


        currentAnimation = animationMap.get(direction);
        currentAnimation.update();
    }

    public void draw(Graphics2D gfx) {
        if (showBounds) {
            this.drawBounds(gfx);
        }

        gfx.drawImage(currentAnimation.getCurrentFrame().getImage(), pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale, null);
    }
}
