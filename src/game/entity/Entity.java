package game.entity;

import java.awt.*;


public abstract class Entity {
    protected int xPos, yPos;
    protected int width, height;
    protected int xVel, yVel;

    protected abstract void update();

    protected abstract void draw(Graphics2D gfx);
}
