package game.canvas;

import game.GameLogic;
import game.data.GameData;

import java.awt.*;

public class GameCanvas2D extends RenderCanvas2D {
    private final GameLogic gameLogic;

    public GameCanvas2D(GameData gameData, GameLogic gameLogic) {
        super(gameData, gameData.getGameScreenSize());

        this.gameLogic = gameLogic;
    }

    @Override
    public void update() {
        gameLogic.update();
    }

    @Override
    public void draw(Graphics2D gfx) {
        gameLogic.draw(gfx);
    }
}