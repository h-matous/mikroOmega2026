package game.data;

import game.utilities.Vector2i;

public class Collider {
    private final Vector2i pos;
    private final Vector2i size;

    private boolean isEnabled;

    public Collider(Vector2i pos, Vector2i size) {
        this.pos = pos;
        this.size = size;

        this.isEnabled = true;
    }

    public Collider(Vector2i pos, Vector2i size, boolean isEnabled) {
        this.pos = pos;
        this.size = size;

        this.isEnabled = isEnabled;
    }

    public Vector2i getPos() {
        return pos;
    }

    public Vector2i getSize() {
        return size;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void disable() {
        this.isEnabled = false;
    }

    public void enable() {
        this.isEnabled = true;
    }
}
