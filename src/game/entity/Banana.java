package game.entity;

import game.Texture;

import game.Vector2i;
import game.data.GameData;

import java.awt.*;

public class Banana extends Entity {
    private Vector2i maxFallingVel;

    Texture texture;


    public Banana(GameData gameData) {

        setDefaultValues(gameData);
    }

    //TODO: Implement Collider map and rotation pivot point map for each banana variant
    public void setDefaultValues(GameData gameData) {
        this.texture = gameData.getTexMngr().getTexture("banana-1");

        this.pos = new Vector2i(0, 0);
        this.scale = gameData.getConstants().getScale();
        this.size = new Vector2i(texture.getWidth());
        this.vel = new Vector2i();

        //TODO: Do something IDK
        this.collider = new Collider(new Vector2i(28, 23), new Vector2i(9, 14));
        //TOOD: maybe this too
        this.maxFallingVel = new Vector2i(0, 5);

        this.rotation = 0;
    }

    @Override
    public void update(GameData gameData) {
        super.update(gameData);

        //TODO: Implement proper collisions and falling movement
        pos.add(maxFallingVel);
        rotation = rotation + 5;
    }

    @Override
    public void draw(Graphics2D gfx) {
        super.draw(gfx);

        gfx.drawImage(texture.getRotatedInstance(rotation).getImage(), pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale, null);
    }
}