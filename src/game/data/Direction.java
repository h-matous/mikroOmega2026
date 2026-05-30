package game.data;

import game.utilities.Vector2i;

/**
 * The Direction Enum represents each Direction as a 2D int vector
 */
public enum Direction {
    IDLE(new Vector2i(0, 0)),
    UP(new Vector2i(0, -1)),
    DOWN(new Vector2i(0, 1)),
    LEFT(new Vector2i(-1, 0)),
    RIGHT(new Vector2i(1, 0));

    private final Vector2i dir;

    /**
     * Constructor sets its vector direction
     * @param dir vector direction as a 2D int vector
     */
    Direction(Vector2i dir) {
        this.dir = dir;
    }

    /**
     * Used for getting the vector representing the Direction
     * @return returns the Direction as a 2D int vector
     */
    public Vector2i getVector() {
        return dir;
    }
}