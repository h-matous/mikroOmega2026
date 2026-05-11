package game;

import game.background.AnimatedBackground;
import game.entity.Banana;
import game.entity.Player;

import java.awt.*;
import java.util.Random;

public class GameLogic {
    private Dimension screenSize;

    private int scale;

    private final KeyHandler keyH;

    private final int targetUPS;

    private final TextureManager texMngr;
    private final Player player;
    private final Banana exampleBanana;
    private final Score score;

    private final AnimatedBackground bg;

    private final Random rnd;

    public GameLogic(Dimension screenSize, KeyHandler keyH, int targetUPS) {
        this.screenSize = screenSize;

        this.scale = 5;

        this.keyH = keyH;

        this.targetUPS = targetUPS;


        rnd = new Random();


        texMngr = new TextureManager();

        player = new Player(scale, keyH, texMngr, targetUPS);
        exampleBanana = new Banana(scale, texMngr, targetUPS);
        score = new Score(screenSize);

        bg = new AnimatedBackground(screenSize, rnd, targetUPS);

    }

    public void update() {
        bg.update();
        score.addScore(1);
        exampleBanana.update();
        player.update();
    }

    public void paint(Graphics2D gfx) {
        bg.draw(gfx);
        score.draw(gfx);
        exampleBanana.draw(gfx);
        player.draw(gfx);
    }

    public KeyHandler getKeyHandler() {
        return keyH;
    }
}
