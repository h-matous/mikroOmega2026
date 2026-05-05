package game.entity;

import game.Canvas2D;
import game.KeyHandler;
import game.TextureManager;

import java.awt.*;
import java.util.HashMap;

public class Player extends Entity {
    KeyHandler keyH;

    private int maxVel;

    private enum Direction {
        IDLE, LEFT, RIGHT
    }

    private Direction direction;

    private HashMap<Direction, Animation> animationMap;
    private Animation currentAnimation;


    public Player(KeyHandler keyH, TextureManager texMngr, int targetUPS) {
        this.keyH = keyH;

        setDefaultValues();

        initializeAnimationMap(texMngr, targetUPS);
    }

    public void setDefaultValues() {
        this.xPos = 100;
        this.yPos = 100;

        this.width = 150;
        this.height = 150;

        this.xVel = 0;
        this.yVel = 0;

        direction = Direction.IDLE;

        this.maxVel = 4;
    }



    public void initializeAnimationMap(TextureManager texMngr, int targetUPS) {
        animationMap = new HashMap<>();

        animationMap.put(Direction.IDLE, new Animation(texMngr.getTexture("monkey-idle"), 1, 1, targetUPS));
        animationMap.put(Direction.LEFT, new Animation(texMngr.getTexture("monkey-walk-left"), 8, 18, targetUPS));
        animationMap.put(Direction.RIGHT, new Animation(texMngr.getTexture("monkey-walk-right"), 8, 18, targetUPS));

        currentAnimation = animationMap.get(direction);
    }

    public void update() {
        xVel = 0;
        yVel = 0;

        if (keyH.isUpPressed()) {
            yVel = yVel - maxVel;
            direction = Direction.IDLE;
        }
        if (keyH.isDownPressed()) {
            yVel = yVel + maxVel;
            direction = Direction.IDLE;
        }
        if (keyH.isLeftPressed()) {
            xVel = xVel - maxVel;
            direction = Direction.LEFT;
        }
        if (keyH.isRightPressed()) {
            xVel = xVel + maxVel;
            direction = Direction.RIGHT;
        }

        xPos = xPos + xVel;
        yPos = yPos + yVel;

        if (xVel == 0) direction = Direction.IDLE;

        currentAnimation = animationMap.get(direction);
        currentAnimation.update();
    }

    public void draw(Graphics2D gfx) {
        gfx.drawImage(currentAnimation.getCurrentFrame().getImage(), xPos, yPos, width, height, null);
    }
}
