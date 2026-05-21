package game;

import game.background.AnimatedBackground;
import game.data.GameData;
import game.entity.Banana;
import game.entity.Player;

import java.awt.*;

public class GameLogic {
    private final GameData gameData;

    private final Player player;
    private final Banana exampleBanana;
    private final Score score;

    private final AnimatedBackground bg;



    public GameLogic(GameData gameData) {
        this.gameData = gameData;

        this.player = new Player(gameData);
        this.exampleBanana = new Banana(gameData);
        this.score = new Score(gameData.getGameScreenSize());

        this.bg = new AnimatedBackground(gameData, gameData.getGameScreenSize());
    }

    public void update() {
        this.bg.update(gameData);
        this.score.addScore(1);
        this.exampleBanana.update(gameData);
        this.player.update(gameData);

        if (player.collidesWith(exampleBanana)) {
            System.out.println("COLLISION DETECTED");
            //exampleBanana.getCollider().disable();
        }
    }

    public void draw(Graphics2D gfx) {
        this.bg.draw(gfx);
        this.score.draw(gfx);
        this.exampleBanana.draw(gfx);
        this.player.draw(gfx);
    }
}