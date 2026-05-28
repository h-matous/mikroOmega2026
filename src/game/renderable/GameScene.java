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

    private int spawnUpdateCounter;
    private int bananaSpawnDelay;

    public GameScene(GameData gameData) {
        this.player = new Player(gameData);

        this.bananas = new ArrayList<>();

        this.score = new Score(gameData.getPlayerScoreData(), gameData.getGameScreenSize());

        this.bg = new AnimatedBackground(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getGameScreenSize());


        //Counts how many updates have occured from the last Banana spawn
        this.spawnUpdateCounter = 0;
        //Current number of updates (ticks/steps) before spawning a new Banana
        this.bananaSpawnDelay = gameData.calculateCollectableSpawnTickDelay();
    }


    private void bananaChecking(GameData gameData) {
        //For every Banana in bananas
        for (int i = 0; i < bananas.size(); i++) {
            Banana banana = bananas.get(i);

            //If Player collected the banana, the score increases and the banana is removed
            if (player.collidesWith(banana)) {
                banana.disableCollider();
                score.addScore(banana.getCollectibleScore());
                bananas.remove(i);
                i--;

                //Since the bananaSpawnDelay is based on current Score of the Player it needs to be updated
                bananaSpawnDelay = gameData.calculateCollectableSpawnTickDelay();
            }
            //Else if the banana fell off-screen then the game is over
            else if (banana.hasFallenOffScreen(gameData)) {
                bananas.remove(i);
                i--;

                //TODO: Game over
                System.out.println("Game over!");
            }
        }
    }

    @Override
    public void update(GameData gameData) {
        spawnUpdateCounter++;

        this.bg.update(gameData);
        this.score.update(gameData);

        this.player.update(gameData);

        if (spawnUpdateCounter >= bananaSpawnDelay) {
            spawnUpdateCounter = 0;
            bananas.add(new Banana(gameData));
        }

        for (Banana banana : bananas) {
            banana.update(gameData);
        }

        bananaChecking(gameData);
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