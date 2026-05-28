package game.renderable.entity;

import game.texture.Texture;

import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;

public class Banana extends Entity {
    private int fallingVel;

    private Texture texture;

    //Represents how much score should the player receive after collecting this Banana
    private int collectibleScore;

    private int rotationSpeed;


    public Banana(GameData gameData) {
        setDefaultValues(gameData);
    }

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


        this.fallingVel = gameData.getCurrentCollectibleFallingVel();

        this.rotation = 0;

        this.colliderEnabled = true;

        this.collectibleScore = gameData.getConstants().getCollectableScore(id);

        this.rotationSpeed = gameData.getConstants().getMaxCollectableRotationSpeed();

        //Random rotation direction
        if (gameData.getRnd().nextBoolean()) {
            this.rotationSpeed = -rotationSpeed;
        }
    }

    public int getCollectibleScore() {
        return collectibleScore;
    }

    public boolean hasFallenOffScreen(GameData gameData) {
        return this.pos.getY() + this.collider.getPos().getY() > gameData.getGameScreenSize().height;
    }

    @Override
    public void update(GameData gameData) {
        super.update(gameData);

        pos.add(0, fallingVel);
        rotation = rotation + rotationSpeed;
    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        super.draw(gfx, gameData);

        if (!gameData.getConstants().isEntityRotationDisabled()) {
            gfx.drawImage(this.texture.getRotatedInstance(rotation).getImage(), pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale), null);
        }
        else {
            gfx.drawImage(this.texture.getImage(), pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale), null);
        }
    }
}