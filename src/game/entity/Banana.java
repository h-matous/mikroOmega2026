package game.entity;

import game.Texture;
import game.TextureManager;
import game.Vector2i;

import java.awt.*;

public class Banana extends Entity {
    private int maxVel;

    Texture texture;


    public Banana(int scale, TextureManager texMngr, int targetUPS) {

        setDefaultValues(scale, texMngr);
    }

    //TODO: Implement Collider map and rotation pivot point map for each banana variant
    public void setDefaultValues(int scale, TextureManager texMngr) {
        this.texture = texMngr.getTexture("banana-1");

        this.pos = new Vector2i(0, 0);
        this.scale = scale;
        this.size = new Vector2i(texture.getWidth());
        this.vel = new Vector2i();

        this.collider = new Collider(new Vector2i(28, 23), new Vector2i(9, 14));

        this.maxVel = 5;

        this.rotation = 0;
    }

    @Override
    public void update() {
        //TODO: Implement proper collisions and falling movement
        //pos.addY(maxVel);
        rotation = rotation + 3;
    }

    @Override
    public void draw(Graphics2D gfx) {
        if (showBounds) {
            this.drawBounds(gfx);
        }

        gfx.drawImage(texture.getRotatedInstance(rotation).getImage(), pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale, null);
    }
}