package game;

import game.entity.Player;

import java.awt.*;

public class GameLogic {
    private Dimension screenSize;

    private KeyHandler keyH;

    private final int targetUPS;

    private final TextureManager texMngr;
    private Player player;
    private Score score;

    private AnimatedBackground bg;

    public GameLogic(Dimension screenSize, KeyHandler keyH, int targetUPS) {
        this.screenSize = screenSize;

        this.keyH = keyH;

        this.targetUPS = targetUPS;

        texMngr = new TextureManager();

        player = new Player(keyH, texMngr, targetUPS);
        score = new Score(screenSize);

        bg = new AnimatedBackground(screenSize, targetUPS);
    }

    public void update() {
        player.update();
        bg.update();
    }

    public void paint(Graphics2D gfx) {
        score.draw(gfx);
        bg.draw(gfx);
        player.draw(gfx);
    }

    public KeyHandler getKeyHandler() {
        return keyH;
    }
}
