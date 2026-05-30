package game;

import game.data.GameData;
import game.data.GameState;
import game.renderable.GameScene;
import game.screen.GameScreen;
import game.screen.StatsScreen;
import game.screen.TitleScreen;

import javax.swing.*;

/**
 * The Game class is the root holder for gameData and all Screens, it is responsible for changing the gameStates
 */
public class Game {
    private final GameData gameData;

    private final TitleScreen titleScreen;
    private final GameScreen gameScreen;
    private final StatsScreen statsScreen;

    private final GameScene gameScene;


    /**
     * Constructor to initialise all Screens (JFrames/windows)
     */
    public Game() {
        configUI();

        gameData = new GameData(this);

        gameScene = new GameScene(gameData);

        titleScreen = new TitleScreen(gameData);
        gameScreen = new GameScreen(gameData, gameScene);
        statsScreen = new StatsScreen(gameData);
    }

    /**
     * Starts the game
     */
    public void start() {
        gameData.changeGameState(gameData.getGameState());
    }


    /**
     * Used to apply Screen changes from a gameState change
     * @param state the new GameState
     */
    public void revalidateFromGameState(GameState state) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(()->revalidateFromGameState(state));
            return;
        }

        //TODO: Complete
        System.out.println(state);


        //State machine
        switch (state) {
            case TITLE_MAIN:
                titleScreen.setVisible(true);
                gameScreen.setVisible(false);
                statsScreen.setVisible(false);

                titleScreen.setMainPanelVisibility(true);
                titleScreen.setSettingsPanelVisibility(false);

                titleScreen.setEnabledForMainPanelButtons(true);

                titleScreen.startThread();
                break;

            case TITLE_STATISTICS:
                titleScreen.setVisible(true);
                gameScreen.setVisible(false);
                statsScreen.setVisible(true);

                titleScreen.setMainPanelVisibility(true);
                titleScreen.setSettingsPanelVisibility(false);

                titleScreen.setEnabledForMainPanelButtons(true);

                titleScreen.startThread();
                break;

            case TITLE_SETTINGS:
                titleScreen.setVisible(true);
                gameScreen.setVisible(false);
                statsScreen.setVisible(false);

                titleScreen.setMainPanelVisibility(true);
                titleScreen.setSettingsPanelVisibility(true);

                titleScreen.setEnabledForMainPanelButtons(false);

                titleScreen.startThread();
                break;

            case GAME_MAIN:
                titleScreen.stopThread();

                titleScreen.setVisible(false);
                gameScreen.setVisible(true);
                statsScreen.setVisible(false);

                gameScreen.startThread();
                break;

            case GAME_PAUSE:
                break;

            case GAME_LOST:
                break;
        }
    }


    /**
     * Used for configuring the UserInterface flags
     */
    private static void configUI() {
        setUILookAndFeel();
        setUIScalingProperty();
    }

    /**
     * Used for setting the UI LookAndFeel to system's appearance
     */
    private static void setUILookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to set UI system LookAndFeel: " + e.getMessage());
        }
    }

    /**
     * Used for telling the operating system that this Game is aware of scaling
     */
    private static void setUIScalingProperty() {
        try {
            System.setProperty("sun.java2d.uiScale", "1");
        }
        catch (SecurityException | NullPointerException | IllegalArgumentException e) {
            System.out.println("Failed to set UI system Scale property: " + e.getMessage());
        }
    }
}