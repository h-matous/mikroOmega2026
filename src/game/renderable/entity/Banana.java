package game.renderable.entity;

import game.texture.Texture;

import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;

public class Banana extends Entity {
    private Vector2i fallingVel;

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


        this.pos = new Vector2i(0, 0);
        this.scale = gameData.getConstants().getScale();
        this.size = new Vector2i(texture.getWidth(), texture.getWidth());
        this.vel = new Vector2i();


        this.collider = gameData.getConstants().getCollider(id);

        this.fallingVel = gameData.getCurrentCollectibleFallingVel();

        this.rotation = 0;

        this.colliderEnabled = true;

        this.collectibleScore = gameData.getConstants().getCollectibleScore(id);

        this.rotationSpeed = gameData.getConstants().getMaxCollectibleRotationSpeed();

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

        pos.add(fallingVel);
        rotation = rotation + rotationSpeed;
    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        super.draw(gfx, gameData);

        gfx.drawImage(texture.getRotatedInstance(rotation).getImage(), pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale, null);
    }
}