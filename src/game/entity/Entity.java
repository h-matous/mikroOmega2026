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

    public Collider getCollider() {
        return collider;
    }

    //TODO: Complete this method
    public boolean collidesWith(Entity other) {
        int thisX1 = this.pos.getX() + this.collider.getPos().getX() * this.collider.getScale();
        int otherX1 = other.pos.getX() + other.collider.getPos().getX() * other.collider.getScale();

        boolean cond1 = thisX1 > otherX1; //&& this.pos.getX() + this.collider.getPos().getX() + this.collider.getSize().getX() * this.collider.getScale() > other.pos.getX() + other.collider.getPos().getX() + other.collider.getSize().getX() * other.collider.getScale();

        return cond1;
    }


    public abstract void update();

    public abstract void draw(Graphics2D gfx);

    protected void drawBounds(Graphics2D gfx) {
        gfx.setColor(Color.BLACK);
        //Rotated bounds for the banana
        Vector2i UL = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) + (size.getX() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) - (size.getY() * scale / 2.0) * Math.sin(Math.toRadians(rotation))), (int) ((pos.getY() + size.getY() * scale / 2.0) + (size.getY() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) + (size.getX() * scale / 2.0) * Math.sin(Math.toRadians(rotation))));
        Vector2i UR = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) - (size.getX() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) - (size.getX() * scale / 2.0) * Math.sin(Math.toRadians(rotation))), (int) ((pos.getY() + size.getY() * scale / 2.0) + (size.getY() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) - (size.getX() * scale / 2.0) * Math.sin(Math.toRadians(rotation))));

        Vector2i BL = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) + (size.getX() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) + (size.getY() * scale / 2.0) * Math.sin(Math.toRadians(rotation))), (int) ((pos.getY() + size.getY() * scale / 2.0) - (size.getY() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) + (size.getX() * scale / 2.0) * Math.sin(Math.toRadians(rotation))));
        Vector2i BR = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) - (size.getX() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) + (size.getX() * scale / 2.0) * Math.sin(Math.toRadians(rotation))), (int) ((pos.getY() + size.getY() * scale / 2.0) - (size.getY() * scale / 2.0) * Math.cos(Math.toRadians(rotation)) - (size.getX() * scale / 2.0) * Math.sin(Math.toRadians(rotation))));

        gfx.drawLine(UL.getX(), UL.getY(), UR.getX(), UR.getY());
        gfx.drawLine(BL.getX(), BL.getY(), BR.getX(), BR.getY());

        gfx.drawLine(BL.getX(), BL.getY(), UL.getX(), UL.getY());
        gfx.drawLine(BR.getX(), BR.getY(), UR.getX(), UR.getY());



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
