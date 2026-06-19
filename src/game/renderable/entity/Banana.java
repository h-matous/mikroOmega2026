package game.renderable.entity;

import game.texture.Texture;

import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;

/**
 * The class Banana is an extension of the Entity class, it represents a falling Banana collectable
 */
public class Banana extends Entity {
    private int fallingVel;

    protected Texture texture;

    //Represents how much score should the player receive after collecting this Banana
    private int collectibleScore;

    protected int rotationSpeed;

    /**
     * Constructor sets the default values
     * @param gameData data of the Game
     */
    public Banana(GameData gameData) {
        setDefaultValues(gameData);
    }

    /**
     * Used for setting the default values of the Banana
     * @param gameData data of the Game
     */
    public void setDefaultValues(GameData gameData) {
        String id = "banana-" + gameData.getRnd().nextInt(1, gameData.getBananaVariationsCount() + 1);

        this.texture = gameData.getTexMngr().getTexture(id);

        //Random flip transformation
        if (gameData.getRnd().nextBoolean()) {
            this.texture = texture.getHorizontallyMirroredInstance();
        }
        if (gameData.getRnd().nextBoolean()) {
            this.texture = texture.getVerticallyMirroredInstance();
        }

        this.collider = gameData.getConstants().getCollider(id);

        this.scale = gameData.getScale();
        this.size = new Vector2i(texture.getWidth(), texture.getHeight());
        this.pos = new Vector2i(gameData.getRnd().nextInt((int) (-1 * this.collider.getPos().getX() * this.scale), (int) (gameData.getGameScreenSize().width - this.size.getX() * this.scale + (this.size.getX() - this.collider.getPos().getX() - this.collider.getSize().getX()) * this.scale)), (int) (-1 * this.size.getY() * this.scale));

        this.vel = new Vector2i();


        this.fallingVel = gameData.getCurrentCollectableFallingSpeed();

        this.rotation = 0;

        if (!gameData.isCollectableRotationDisabled()) {
            this.rotation = rotation + gameData.getRnd().nextInt(0, 360);
        }

        this.colliderEnabled = true;

        this.collectibleScore = gameData.getConstants().getCollectableScore(id);

        this.rotationSpeed = gameData.getConstants().getMaxCollectableRotationSpeed();

        //Random rotation direction
        if (gameData.getRnd().nextBoolean()) {
            this.rotationSpeed = -rotationSpeed;
        }
    }

    /**
     * Used for getting the score this collectable Banana should give to the Player after collecting
     * @return returns an int representing the player score offset
     */
    public int getCollectibleScore() {
        return collectibleScore;
    }

    /**
     * Used for checking whether the collectable Banana has fallen off-screen
     * @param gameData data of the Game
     * @return returns a boolean representing the condition
     */
    public boolean hasFallenOffScreen(GameData gameData) {
        return this.pos.getY() + this.collider.getPos().getY() > gameData.getGameScreenSize().height;
    }

    /**
     * Used for updating the Banana Entity at a fixed time step,
     * the Banana collectable Entity is rotated and falls to the ground
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {
        super.update(gameData);

        pos.add(0, fallingVel);

        if (!gameData.isCollectableRotationDisabled()) {
            rotation = rotation + rotationSpeed;
        }
    }

    /**
     * Used for drawing the collectable Banana Entity
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        super.draw(gfx, gameData);

        if (!gameData.isCollectableRotationDisabled()) {
            gfx.drawImage(this.texture.getRotatedInstance(rotation).getImage(), pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale), null);
        }
        else {
            gfx.drawImage(this.texture.getImage(), pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale), null);
        }
    }
}