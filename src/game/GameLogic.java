package game;

import game.entity.Player;

import java.awt.*;

public class GameLogic {
    private Dimension screenSize;

    private KeyHandler keyH;

    private final int targetUPS;

    private final TextureManager texMngr;
    private Player player;

    private AnimatedBackground bg;

    public GameLogic(Dimension screenSize, KeyHandler keyH, int targetUPS) {
        this.screenSize = screenSize;

        this.keyH = keyH;

        this.targetUPS = targetUPS;

        texMngr = new TextureManager();

        player = new Player(keyH, texMngr, targetUPS);

        bg = new AnimatedBackground(screenSize, targetUPS);
    }

    public void update() {
        player.update();
        bg.update();
    }

    public void paint(Graphics2D gfx) {
        player.draw(gfx);
        bg.draw(gfx);
    }

    public KeyHandler getKeyHandler() {
        return keyH;
    }
}
