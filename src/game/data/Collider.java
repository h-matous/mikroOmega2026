package game.data;

import game.utilities.Vector2i;

public class Collider {
    private final Vector2i pos;
    private final Vector2i size;


    public Collider(Vector2i pos, Vector2i size) {
        this.pos = pos;
        this.size = size;
    }


    public Vector2i getPos() {
        return pos;
    }

    public Vector2i getSize() {
        return size;
    }
}