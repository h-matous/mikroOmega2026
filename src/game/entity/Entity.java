package game.entity;

import game.Vector2i;

import java.awt.*;


public abstract class Entity {
    protected Vector2i pos;
    protected int scale;
    protected Vector2i size;
    protected Vector2i vel;

    protected double rotation;

    protected Collider collider;

    protected static final boolean showBounds = true;

    public abstract void update();

    public abstract void draw(Graphics2D gfx);

    protected void drawBounds(Graphics2D gfx) {
        gfx.setColor(Color.BLACK);
        //TODO: Draw rotated bounds for the banana

        //Texture rectangle
        gfx.drawRect(pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale);

        //For also drawing filled Texture
        gfx.setColor(new Color(0, 255, 0, 25));
        gfx.fillRect(pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale);
        gfx.setColor(Color.BLACK);

        //For also drawing filled Collider
        gfx.setColor(new Color(255, 0, 0, 80));
        gfx.fillRect(pos.getX() + collider.getPos().getX() * scale, pos.getY() + collider.getPos().getY() * scale, collider.getSize().getX() * scale, collider.getSize().getY() * scale);
        gfx.setColor(Color.BLACK);

        //Collider rectangle
        gfx.drawRect(pos.getX() + collider.getPos().getX() * scale, pos.getY() + collider.getPos().getY() * scale, collider.getSize().getX() * scale, collider.getSize().getY() * scale);
    }
}
