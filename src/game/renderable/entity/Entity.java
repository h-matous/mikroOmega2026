package game.renderable.entity;

import game.renderable.DrawableAndUpdatable;
import game.data.Collider;
import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;


public abstract class Entity implements DrawableAndUpdatable {
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

    public boolean collidesWith(Entity other) {
        if (this.collider.isEnabled() && other.getCollider().isEnabled()) {
            //For axis X
            int thisX1 = this.pos.getX() + this.collider.getPos().getX() * this.scale;
            int otherX1 = other.pos.getX() + other.collider.getPos().getX() * other.scale;

            int thisX2 = this.pos.getX() + this.collider.getPos().getX() * this.scale + this.collider.getSize().getX() * this.scale;
            int otherX2 = other.pos.getX() + other.collider.getPos().getX() * other.scale + other.collider.getSize().getX() * other.scale;

            boolean cond1 = otherX1 < thisX2;
            boolean cond2 = otherX2 > thisX1;

            //For axis Y
            int thisY1 = this.pos.getY() + this.collider.getPos().getY() * this.scale;
            int otherY1 = other.pos.getY() + other.collider.getPos().getY() * other.scale;

            int thisY2 = this.pos.getY() + this.collider.getPos().getY() * this.scale + this.collider.getSize().getY() * this.scale;
            int otherY2 = other.pos.getY() + other.collider.getPos().getY() * other.scale + other.collider.getSize().getY() * other.scale;

            boolean cond3 = otherY1 < thisY2;
            boolean cond4 = otherY2 > thisY1;

            //Final condition
            return cond1 && cond2 && cond3 && cond4;
        }

        return false;
    }

    @Override
    public void update(GameData gameData) {

    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        if (showBounds) {
            this.drawBounds(gfx);
        }

    }

    protected void drawBounds(Graphics2D gfx) {
        gfx.setColor(Color.BLACK);

        if (rotation != 0) {
            double rotationRadians = Math.toRadians(rotation);
            double sinOfRotation = Math.sin(rotationRadians);
            double cosOfRotation = Math.cos(rotationRadians);


            //Rotated bounds for the banana
            Vector2i UL = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) + (size.getX() * scale / 2.0) * cosOfRotation - (size.getY() * scale / 2.0) * sinOfRotation), (int) ((pos.getY() + size.getY() * scale / 2.0) + (size.getY() * scale / 2.0) * cosOfRotation + (size.getX() * scale / 2.0) * sinOfRotation));
            Vector2i UR = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) - (size.getX() * scale / 2.0) * cosOfRotation - (size.getX() * scale / 2.0) * sinOfRotation), (int) ((pos.getY() + size.getY() * scale / 2.0) + (size.getY() * scale / 2.0) * cosOfRotation - (size.getX() * scale / 2.0) * sinOfRotation));

            Vector2i BL = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) + (size.getX() * scale / 2.0) * cosOfRotation + (size.getY() * scale / 2.0) * sinOfRotation), (int) ((pos.getY() + size.getY() * scale / 2.0) - (size.getY() * scale / 2.0) * cosOfRotation + (size.getX() * scale / 2.0) * sinOfRotation));
            Vector2i BR = new Vector2i((int) ((pos.getX() + size.getX() * scale / 2.0) - (size.getX() * scale / 2.0) * cosOfRotation + (size.getX() * scale / 2.0) * sinOfRotation), (int) ((pos.getY() + size.getY() * scale / 2.0) - (size.getY() * scale / 2.0) * cosOfRotation - (size.getX() * scale / 2.0) * sinOfRotation));

            gfx.drawLine(UL.getX(), UL.getY(), UR.getX(), UR.getY());
            gfx.drawLine(BL.getX(), BL.getY(), BR.getX(), BR.getY());

            gfx.drawLine(BL.getX(), BL.getY(), UL.getX(), UL.getY());
            gfx.drawLine(BR.getX(), BR.getY(), UR.getX(), UR.getY());

        }

        //Whole Texture rectangle outline
        gfx.drawRect(pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale);

        //Whole Texture rectangle filling
        gfx.setColor(new Color(0, 255, 0, 25));
        gfx.fillRect(pos.getX(), pos.getY(), size.getX() * scale, size.getY() * scale);
        gfx.setColor(Color.BLACK);


        //Collider rectangle outline
        gfx.drawRect(pos.getX() + collider.getPos().getX() * scale, pos.getY() + collider.getPos().getY() * scale, collider.getSize().getX() * scale, collider.getSize().getY() * scale);

        if (this.collider.isEnabled()) {
            //Collider rectangle filling
            gfx.setColor(new Color(255, 0, 0, 80));
            gfx.fillRect(pos.getX() + collider.getPos().getX() * scale, pos.getY() + collider.getPos().getY() * scale, collider.getSize().getX() * scale, collider.getSize().getY() * scale);
        }
    }
}
