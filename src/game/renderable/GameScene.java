package game.renderable;

import game.data.GameState;
import game.data.statistics.Statistic;
import game.renderable.background.AnimatedBackground;
import game.data.GameData;
import game.renderable.entity.Banana;
import game.renderable.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameScene class implements the DrawableAndUpdatable interface, and it holds and manages the whole gameplay happening on the GameScreen
 */
public class GameScene implements DrawableAndUpdatable {

    private final Player player;
    private final List<Banana> bananas;
    private final Score score;

    private final AnimatedBackground bg;

    private int spawnUpdateCounter;
    private int bananaSpawnDelay;

    private volatile boolean pendingReset;

    /**
     * Constructor sets the default value
     * @param gameData data of the Game
     */
    public GameScene(GameData gameData) {
        this.player = new Player(gameData);

        this.bananas = new ArrayList<>();

        this.score = new Score(gameData.getPlayerScoreData(), gameData.getGameScreenSize());

        this.bg = new AnimatedBackground(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getGameScreenSize());


        this.requestReset();
    }

    /**
     * Used for requesting a GameScene reset
     */
    public void requestReset() {
        this.pendingReset = true;
    }

    /**
     * Used for resetting the GameScene for another gameplay
     */
    private void reset(GameData gameData) {
        this.player.reset(gameData);
        this.bananas.clear();
        this.score.reset(gameData.getPlayerScoreData(), gameData.getGameScreenSize());
        this.bg.reset(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getGameScreenSize());

        //Counts how many updates have occured from the last Banana spawn
        this.spawnUpdateCounter = 0;

        //Current number of updates (ticks/steps) before spawning a new Banana
        this.bananaSpawnDelay = gameData.calculateCollectableSpawnTickDelay();
    }


    /**
     * Used for getting the Player instance which contains the current gameplay Statistic,
     * which is created after a collectable Banana Entity has fallen off-screen
     * @return returns the player as a Player Entity
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Used for checking if any of the Bananas touched the Player's basket Collider, then the Banana is collected and the Player's Score increases,
     * it is also responsible for checking whether any Banana has fallen off-screen, then the Game ends
     * @param gameData data of the Game
     */
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

                //Create a new Player Statistic to log the current Date and Time
                player.setPlayerStatistic(new Statistic(gameData.getPlayerScoreData(), gameData.getChosenInputMethod()));
                System.out.println(player.getPlayerStatistic());

                //Set the GameState to be GameState.GAME_LOST (GameOver)
                gameData.changeGameState(GameState.GAME_LOST);
            }
        }
    }


    /**
     * Used for updating the GameScene at a fixed time step,
     * responsible for managing the gameplay realtime
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {
        if (pendingReset) {
            reset(gameData);
            this.pendingReset = false;
        }

        if (gameData.getKeyHandler().hasEscapePressed()) {
            gameData.togglePause();
        }

        //If the current GameState is GAME_MAIN => the GameState is not GAME_PAUSED/GAME_LOST
        if (gameData.getGameState() == GameState.GAME_MAIN) {
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
    }

    /**
     * Used for drawing all the contents of the GameScene
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
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