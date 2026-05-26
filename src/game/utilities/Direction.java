package game.utilities;

public enum Direction {
    IDLE(new Vector2i(0, 0)),
    UP(new Vector2i(0, -1)),
    DOWN(new Vector2i(0, 1)),
    LEFT(new Vector2i(-1, 0)),
    RIGHT(new Vector2i(1, 0));

    private final Vector2i dir;

    Direction(Vector2i dir) {
        this.dir = dir;
    }

    public Vector2i getVector() {
        return dir;
    }
}