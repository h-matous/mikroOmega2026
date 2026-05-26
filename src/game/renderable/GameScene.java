package game.renderable;

import game.renderable.background.AnimatedBackground;
import game.data.GameData;
import game.renderable.entity.Banana;
import game.renderable.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScene implements DrawableAndUpdatable {

    private final Player player;
    private final List<Banana> bananas;
    private final Score score;

    private final AnimatedBackground bg;


    public GameScene(GameData gameData) {
        this.player = new Player(gameData);

        this.bananas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            bananas.add(new Banana(gameData));
        }

        this.score = new Score(gameData.getGameScreenSize());

        this.bg = new AnimatedBackground(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getGameScreenSize());
    }

    @Override
    public void update(GameData gameData) {
        this.bg.update(gameData);
        this.score.update(gameData);

        this.player.update(gameData);


        for (Banana banana : bananas) {
            if (player.collidesWith(banana)) {
                //TODO: Player collected the banana, increase score
                System.out.println("COLLISION DETECTED");
                //banana.disableCollider();
            }

            if (banana.hasFallenOffScreen(gameData)) {
                //TODO: GameOver, player lost
            }

            banana.update(gameData);
        }


        this.score.addScore(1);


    }

    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        this.bg.draw(gfx, gameData);
        this.score.draw(gfx, gameData);

        for (Banana banana : bananas) {
            banana.draw(gfx, gameData);
        }

        this.player.draw(gfx, gameData);
    }
}