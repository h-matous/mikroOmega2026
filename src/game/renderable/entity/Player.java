package game.renderable.entity;

import game.data.PlayerAnimationData;
import game.utilities.Animation;
import game.utilities.Direction;
import game.utilities.KeyHandler;
import game.texture.TextureManager;
import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;
import java.util.HashMap;

public class Player extends Entity {

    private Direction direction;

    private HashMap<Direction, Animation> animationMap;
    private Animation currentAnimation;


    public Player(GameData gameData) {
        initializeAnimationMap(gameData);

        setDefaultValues(gameData);
    }

    public void setDefaultValues(GameData gameData) {
        this.collider = gameData.getConstants().getCollider("player");

        this.scale = gameData.getConstants().getScale();
        this.size = new Vector2i(currentAnimation.getCurrentFrame().getWidth());
        this.pos = new Vector2i(gameData.getGameScreenSize().width / 2 - this.size.getX() * this.scale / 2, gameData.getGameScreenSize().height - this.size.getY() * this.scale - gameData.playerPxOffGameScreenGround());

        this.vel = new Vector2i();

        this.rotation = 0;

        this.colliderEnabled = true;
    }



    public void initializeAnimationMap(GameData gameData) {
        animationMap = new HashMap<>();

        TextureManager texMngr = gameData.getTexMngr();
        int targetUPS = gameData.getConstants().getTargetUPS();


        for (Direction direction : gameData.getConstants().getPlayerAnimationDataMap().keySet()) {
            PlayerAnimationData animationData = gameData.getConstants().getPlayerAnimationData(direction);

            animationMap.put(direction, new Animation(texMngr.getTexture(animationData.getTextureId()), animationData.getFrameCount(), animationData.getTargetAnimFPS(), targetUPS));
        }

        this.direction = gameData.getConstants().getPlayerInitialDirection();

        this.currentAnimation = animationMap.get(direction);
    }



    @Override
    public void update(GameData gameData) {
        super.update(gameData);

        direction = Direction.IDLE;
        vel.setBoth(0 ,0);

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