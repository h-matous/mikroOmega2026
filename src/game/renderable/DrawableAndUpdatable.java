package game.renderable;

import game.data.GameData;

import java.awt.*;

public interface DrawableAndUpdatable {
    void update(GameData gameData);

    void draw(Graphics2D gfx, GameData gameData);
}