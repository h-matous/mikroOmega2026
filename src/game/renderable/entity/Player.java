package game.renderable.entity;

import game.data.InputMethod;
import game.data.PlayerAnimationData;
import game.data.statistics.Statistic;
import game.utilities.Animation;
import game.data.Direction;
import game.utilities.input.KeyHandler;
import game.texture.TextureManager;
import game.utilities.Vector2i;
import game.data.GameData;
import game.utilities.input.MouseHandler;

import java.awt.*;
import java.util.HashMap;

/**
 * The class Player is an extension of the Entity class, it represents a Player
 */
public class Player extends Entity {

    private Direction direction;

    private HashMap<Direction, Animation> animationMap;
    private Animation currentAnimation;

    private Statistic playerStat;

    /**
     * Constructor sets the default values
     * @param gameData data of the Game
     */
    public Player(GameData gameData) {
        initializeAnimationMap(gameData);

        this.pos = new Vector2i();
        this.size = new Vector2i();

        this.vel = new Vector2i();

        reset(gameData);
    }

    /**
     * Used for setting the default values of the Player
     * @param gameData data of the Game
     */
    public void reset(GameData gameData) {
        this.collider = gameData.getConstants().getCollider("player");

        this.direction = gameData.getConstants().getPlayerInitialDirection();

        this.currentAnimation = animationMap.get(direction);

        currentAnimation.resetCurrentFrameCounter();

        this.scale = gameData.getScale();
        this.size.setBoth(currentAnimation.getCurrentFrame().getWidth(), currentAnimation.getCurrentFrame().getHeight());
        this.pos.setBoth((int) (gameData.getGameScreenSize().width / 2.0 - this.size.getX() * this.scale / 2.0), (int) (gameData.getGameScreenSize().height - this.size.getY() * this.scale - gameData.playerPxOffGameScreenGround()));

        this.vel.setBoth(0);

        this.rotation = 0;

        this.colliderEnabled = true;


        playerStat = null;
    }


    /**
     * Used to initialize the Player's Animation HashMap
     * @param gameData data of the Game
     */
    public void initializeAnimationMap(GameData gameData) {
        animationMap = new HashMap<>();

        TextureManager texMngr = gameData.getTexMngr();
        int targetUPS = gameData.getConstants().getTargetUPS();


        for (Direction direction : gameData.getConstants().getPlayerAnimationDataMap().keySet()) {
            PlayerAnimationData animationData = gameData.getConstants().getPlayerAnimationData(direction);

            animationMap.put(direction, new Animation(texMngr.getTexture(animationData.getTextureId()), animationData.getFrameCount(), animationData.getTargetAnimFPS(), targetUPS));
        }
    }

    /**
     * Used for getting the Player's Statistic
     * @return returns the playerStat as a Statistic
     */
    public Statistic getPlayerStatistic() {
        return playerStat;
    }

    /**
     * Used for setting the Player's Statistic
     * @param playerStat the playerStat as a Statistic
     */
    public void setPlayerStatistic(Statistic playerStat) {
        this.playerStat = playerStat;
    }

    /**
     * Used for updating the Player Entity at a fixed time step,
     * the Player is moved accordingly after reacting to the chosen input method,
     * the Animation is also updated if the Player Animation is enabled
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {
        super.update(gameData);

        direction = Direction.IDLE;
        vel.setBoth(0 ,0);

        if (gameData.getChosenInputMethod() == InputMethod.KEYBOARD) {
            KeyHandler keyH = gameData.getKeyHandler();

            if (keyH.isLeftPressed() && this.pos.getX() + this.collider.getPos().getX() * this.scale >= 0) {
                direction = Direction.LEFT;
                vel.add(direction.getVector());
            }
            if (keyH.isRightPressed() && this.pos.getX() + (this.collider.getPos().getX() + this.collider.getSize().getX()) * this.scale <= gameData.getGameScreenSize().width) {
                direction = Direction.RIGHT;
                vel.add(direction.getVector());
            }

            if (vel.getX() == 0) direction = Direction.IDLE;

            vel.multiply(gameData.calculateCurrentPlayerWalkingVel());
        }
        else if (gameData.getChosenInputMethod() == InputMethod.MOUSE) {
            MouseHandler mouseH = gameData.getMouseHandler();

            int currentPlayerXPos = (int) (this.pos.getX() + this.collider.getPos().getX() * this.scale + this.collider.getSize().getX() / 2.0 * this.scale);
            int requestedXPos = mouseH.getMousePosition().getX();

            int deltaX = requestedXPos - currentPlayerXPos;
            float currentMaxWalkingVel = gameData.calculateCurrentPlayerWalkingVel();

            float divisionResult = Math.abs(deltaX / currentMaxWalkingVel);

            /*
            If the cursor is farther away from the centre of the player than the current maximum allowed walking velocity
            then the velocity will be just equal to the maximum walking velocity, else the distance is smaller than the
            maximum velocity => the velocity will be set to be a fraction of the maximum allowed walking velocity
             */
            if (divisionResult >= 1) {
                vel.add((int) currentMaxWalkingVel, 0);
            }
            else {
                vel.add((int) (divisionResult * currentMaxWalkingVel), 0);
            }

            if (deltaX > 0) {
                vel.multiply(1);
                direction = Direction.RIGHT;
            }
            else if (deltaX < 0) {
                vel.multiply(-1);
                direction = Direction.LEFT;
            }
            else {
                vel.multiply(0);
                direction = Direction.IDLE;
            }

        }


        pos.add(vel);


        if (!gameData.isPlayerAnimationDisabled()) {
            currentAnimation = animationMap.get(direction);
            currentAnimation.update();
        }
    }

    /**
     * Used for drawing the Player Entity
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        super.draw(gfx, gameData);

        gfx.drawImage(currentAnimation.getCurrentFrame().getImage(), pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale), null);
    }
}