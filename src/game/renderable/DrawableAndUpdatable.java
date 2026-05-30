package game.renderable;

import game.data.GameData;

import java.awt.*;

/**
 * The interface DrawableAndUpdatable represents renderable Entities/Scenes/Backgrounds
 */
public interface DrawableAndUpdatable {
    /**
     * Used for updating at a fixed time step
     * @param gameData data of the Game
     */
    void update(GameData gameData);

    /**
     * Used for drawing
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    void draw(Graphics2D gfx, GameData gameData);
}