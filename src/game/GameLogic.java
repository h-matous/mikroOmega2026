package game;

import game.entity.Player;

import java.awt.*;

public class GameLogic {
    private KeyHandler keyH;

    private final int targetUPS;

    private final TextureManager texMngr;
    private Player player;



    public GameLogic(KeyHandler keyH, int targetUPS) {
        this.keyH = keyH;

        this.targetUPS = targetUPS;

        texMngr = new TextureManager();

        player = new Player(keyH, texMngr, targetUPS);
    }

    public void update() {
        player.update();
    }

    public void paint(Graphics2D gfx) {
        player.draw(gfx);
    }

    public KeyHandler getKeyHandler() {
        return keyH;
    }
}
