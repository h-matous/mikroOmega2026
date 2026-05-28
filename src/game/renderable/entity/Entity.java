package game.renderable.entity;

import game.renderable.DrawableAndUpdatable;
import game.data.Collider;
import game.utilities.Vector2i;
import game.data.GameData;

import java.awt.*;


public abstract class Entity implements DrawableAndUpdatable {
    protected Vector2i pos;
    protected float scale;
    protected Vector2i size;
    protected Vector2i vel;

    protected double rotation;

    protected Collider collider;
    protected boolean colliderEnabled;


    public boolean collidesWith(Entity other) {
        if (this.colliderEnabled && other.colliderEnabled) {
            //For axis X
            float thisX1 = this.pos.getX() + this.collider.getPos().getX() * this.scale;
            float otherX1 = other.pos.getX() + other.collider.getPos().getX() * other.scale;

            float thisX2 = this.pos.getX() + this.collider.getPos().getX() * this.scale + this.collider.getSize().getX() * this.scale;
            float otherX2 = other.pos.getX() + other.collider.getPos().getX() * other.scale + other.collider.getSize().getX() * other.scale;

            boolean cond1 = otherX1 < thisX2;
            boolean cond2 = otherX2 > thisX1;

            //For axis Y
            float thisY1 = this.pos.getY() + this.collider.getPos().getY() * this.scale;
            float otherY1 = other.pos.getY() + other.collider.getPos().getY() * other.scale;

            float thisY2 = this.pos.getY() + this.collider.getPos().getY() * this.scale + this.collider.getSize().getY() * this.scale;
            float otherY2 = other.pos.getY() + other.collider.getPos().getY() * other.scale + other.collider.getSize().getY() * other.scale;

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
        if (gameData.getConstants().shouldShowEntityBounds()) {
            this.drawBounds(gfx, gameData);
        }

    }

    protected void drawBounds(Graphics2D gfx, GameData gameData) {
        gfx.setColor(Color.BLACK);


        if (rotation != 0 && !gameData.getConstants().isEntityRotationDisabled()) {
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
        gfx.drawRect(pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale));

        //Whole Texture rectangle filling
        gfx.setColor(new Color(0, 255, 0, 25));
        gfx.fillRect(pos.getX(), pos.getY(), (int) (size.getX() * scale), (int) (size.getY() * scale));
        gfx.setColor(Color.BLACK);


        //Collider rectangle outline
        gfx.drawRect((int) (pos.getX() + collider.getPos().getX() * scale), (int) (pos.getY() + collider.getPos().getY() * scale), (int) (collider.getSize().getX() * scale), (int) (collider.getSize().getY() * scale));

        if (this.colliderEnabled) {
            //Collider rectangle filling
            gfx.setColor(new Color(255, 0, 0, 80));
            gfx.fillRect((int) (pos.getX() + collider.getPos().getX() * scale), (int) (pos.getY() + collider.getPos().getY() * scale), (int) (collider.getSize().getX() * scale), (int) (collider.getSize().getY() * scale));
        }
    }

    public void enableCollider() {
        this.colliderEnabled = true;
    }

    public void disableCollider() {
        this.colliderEnabled = false;
    }

    public boolean isColliderEnabled() {
        return colliderEnabled;
    }
}