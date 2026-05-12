package game.entity;

import game.Vector2i;



public class Collider {
    private Vector2i pos;
    private int scale;
    private Vector2i size;

    public Collider(Vector2i pos, int scale, Vector2i size) {
        this.pos = pos;
        this.scale = scale;
        this.size = size;
    }


    public Vector2i getPos() {
        return pos;
    }

    public int getScale() {
        return scale;
    }

    public Vector2i getSize() {
        return size;
    }
}
