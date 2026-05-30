package game.data;

import game.utilities.Vector2i;

/**
 * The Collider class represents a part of the Entity's Texture before any scaling transformations are applied,
 * it is basically a rectangle layered on top of the Texture with its relative coordinates
 */
public class Collider {
    private final Vector2i pos;
    private final Vector2i size;


    /**
     * Fully parametric constructor sets the values
     * @param pos
     * @param size
     */
    public Collider(Vector2i pos, Vector2i size) {
        this.pos = pos;
        this.size = size;
    }


    /**
     * Used for getting the position relative to the 0,0 of the Texture
     * @return returns the position as a 2D int vector
     */
    public Vector2i getPos() {
        return pos;
    }

    /**
     * Used for getting the size of the Collider
     * @return returns the size as a 2D int vector
     */
    public Vector2i getSize() {
        return size;
    }
}