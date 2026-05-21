package game.canvas;

import game.background.AnimatedBackground;
import game.data.GameData;

import java.awt.*;

public class TitleCanvas2D extends RenderCanvas2D {
    private final AnimatedBackground bg;

    public TitleCanvas2D(GameData gameData) {
        super(gameData, gameData.getTitleScreenSize());

        bg = new AnimatedBackground(gameData, gameData.getTitleScreenSize());
    }

    @Override
    public void update() {
        bg.update(gameData);
    }

    @Override
    public void draw(Graphics2D gfx) {
        bg.draw(gfx);
    }
}