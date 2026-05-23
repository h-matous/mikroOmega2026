package game.renderable;

import game.renderable.background.AnimatedBackground;
import game.data.GameData;
import game.renderable.entity.Banana;
import game.renderable.entity.Player;

import java.awt.*;

public class GameLogic implements DrawableAndUpdatable {

    private final Player player;
    private final Banana exampleBanana;
    private final Score score;

    private final AnimatedBackground bg;


    public GameLogic(GameData gameData) {
        this.player = new Player(gameData);
        this.exampleBanana = new Banana(gameData);
        this.score = new Score(gameData.getGameScreenSize());

        this.bg = new AnimatedBackground(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getGameScreenSize());
    }

    @Override
    public void update(GameData gameData) {
        this.bg.update(gameData);
        this.score.update(gameData);
        this.exampleBanana.update(gameData);
        this.player.update(gameData);

        this.score.addScore(1);

        if (player.collidesWith(exampleBanana)) {
            System.out.println("COLLISION DETECTED");
            //exampleBanana.getCollider().disable();
        }
    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        this.bg.draw(gfx, gameData);
        this.score.draw(gfx, gameData);
        this.exampleBanana.draw(gfx, gameData);
        this.player.draw(gfx, gameData);
    }
}