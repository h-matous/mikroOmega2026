package game.screen;

import game.data.GameState;
import game.data.statistics.Statistic;
import game.renderable.GameScene;
import game.data.GameData;

import javax.swing.*;
import java.awt.*;

/**
 * The class GameScreen extends RenderScreen and represents the JFrame in which the GameScene of the Game will be played
 */
public class GameScreen extends RenderScreen {
    private final GameScene gameScene;

    JPanel pausePanel = new JPanel() {
        /**
         * Needed for drawing the overlay over the renderPanel2D,
         * because on a LayeredPane if the top JPanel is set to be opaque,
         * it will not even try to render the JPanel behind
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D gfx = (Graphics2D) g.create();

            gfx.setColor(gameData.getConstants().getSemiTransparentOverlayColor());
            gfx.fillRect(0, 0, this.getWidth(), this.getHeight());

            gfx.dispose();

            super.paintComponent(g);
        }
    };

    JPanel pauseButtonPanel = new JPanel();

    JPanel gameOverPanel = new JPanel() {
        /**
         * Needed for drawing the overlay over the renderPanel2D,
         * because on a LayeredPane if the top JPanel is set to be opaque,
         * it will not even try to render the JPanel behind
         *
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D gfx = (Graphics2D) g.create();

            gfx.setColor(gameData.getConstants().getSemiTransparentOverlayColor());
            gfx.fillRect(0, 0, this.getWidth(), this.getHeight());

            gfx.dispose();

            super.paintComponent(g);
        }
    };

    //Needs to be on the class scope so it can be disabled later
    JButton pbp_pauseButton;

    JButton gop_statisticsButton;
    /**
     * Constructor sets the values
     * @param gameData data of the Game
     * @param gameScene GameScene renderable (DrawableAndUpdatable)
     */
    public GameScreen(GameData gameData, GameScene gameScene) {
        super(gameData, new RenderPanel2D(gameData, gameData.getGameScreenSize(), gameScene, false));

        this.gameScene = gameScene;
        initialize();
    }

    /**
     * Used for initializing the GameScreen
     */
    private void initialize() {
        initPausePanel();
        initPauseButtonPanel();
        initGameOverPanel();

        //Overlays
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(gameData.getGameScreenSize());
        layeredPane.setBounds(0, 0, gameData.getGameScreenSize().width, gameData.getGameScreenSize().height);
        layeredPane.setOpaque(false);


        layeredPane.add(pausePanel, Integer.valueOf(0));
        layeredPane.add(pauseButtonPanel, Integer.valueOf(1));
        layeredPane.add(gameOverPanel, Integer.valueOf(2));

        pausePanel.setFocusable(false);
        pauseButtonPanel.setFocusable(false);

        pausePanel.setVisible(false);
        pauseButtonPanel.setVisible(false);
        gameOverPanel.setVisible(false);


        this.add(layeredPane, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle(gameData.getConstants().getGameName());

        this.setResizable(false);
        this.pack();

        this.setIconImage(gameData.getTexMngr().getTexture("icon").getImage());

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }

    /**
     * Used for initializing the pausePanel of the GameScreen
     */
    private void initPausePanel() {
        //Pause panel
        pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
        pausePanel.setBounds(0, 0, gameData.getGameScreenSize().width, gameData.getGameScreenSize().height);
        pausePanel.setOpaque(false);

        JLabel pp_gameLabel = new JLabel();
        configJLabel(gameData, pp_gameLabel, "Game paused!");


        pausePanel.add(Box.createVerticalGlue());
        pausePanel.add(pp_gameLabel);
        pausePanel.add(Box.createVerticalGlue());

    }


    /**
     * Used for initializing the pauseButtonPanel of the GameScreen,
     * it is separated from the pausePanel so it can also be shown during gameplay
     */
    private void initPauseButtonPanel() {
        //PauseButton panel
        pauseButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pauseButtonPanel.setBounds(0, 0, gameData.getGameScreenSize().width, gameData.getGameScreenSize().height);
        pauseButtonPanel.setOpaque(false);

        pbp_pauseButton = new JButton();

        configJButton(gameData, pbp_pauseButton, "||", x->{gameData.togglePause();});
        pbp_pauseButton.setFocusable(false);

        pbp_pauseButton.setPreferredSize(new Dimension(50, 50));

        pauseButtonPanel.add(pbp_pauseButton);
    }

    /**
     * Used for initializing the gameOverPanel of the GameScreen
     */
    private void initGameOverPanel() {
        //GameOver panel
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
        gameOverPanel.setBounds(0, 0, gameData.getGameScreenSize().width, gameData.getGameScreenSize().height);
        gameOverPanel.setOpaque(false);

        JLabel gop_gameOverLabel = new JLabel();
        configJLabel(gameData, gop_gameOverLabel, "Game Over!");



        JButton gop_restartButton = new JButton();
        //Resets the gameScene
        configJButton(gameData, gop_restartButton, "Restart", x->{gameScene.requestReset();gameData.changeGameState(GameState.GAME_MAIN);});

        JTextField gop_nameTextField = new JTextField(20);
        configJTextField(gameData, gop_nameTextField, "", x->{});
        gop_nameTextField.setMaximumSize(gop_nameTextField.getPreferredSize());

        gop_statisticsButton = new JButton();
        configJButton(gameData, gop_statisticsButton, "Save Score", x->{
            String name = gop_nameTextField.getText().trim();

            if (!name.isBlank()) {
                Statistic playerStatistic = gameScene.getPlayer().getPlayerStatistic();

                if (playerStatistic != null) {
                    gameScene.getPlayer().setPlayerStatistic(null);
                    playerStatistic.setPlayerName(name);
                    gameData.getStatManager().addStatistic(gameData.getConstants(), playerStatistic);

                    gop_nameTextField.setText("");
                    gop_statisticsButton.setEnabled(false);
                }

            }
        });


        JButton gop_backToMenuButton = new JButton();
        //Resets the gameScene
        configJButton(gameData, gop_backToMenuButton, "Back to menu", x->{gameScene.requestReset();gameData.changeGameState(GameState.TITLE_MAIN);});



        gameOverPanel.add(Box.createVerticalGlue());
        gameOverPanel.add(gop_gameOverLabel);
        gameOverPanel.add(Box.createVerticalGlue());

        gameOverPanel.add(gop_restartButton);
        gameOverPanel.add(Box.createVerticalGlue());
        gameOverPanel.add(gop_nameTextField);
        gameOverPanel.add(Box.createVerticalStrut(15));
        gameOverPanel.add(gop_statisticsButton);
        gameOverPanel.add(Box.createVerticalGlue());
        gameOverPanel.add(gop_backToMenuButton);

        gameOverPanel.add(Box.createVerticalGlue());
    }

    /**
     * Used for showing the gameplay and hiding the overlay panels,
     * called from Swing's EDT if the GameState is equal to GAME_MAIN
     */
    public void showGameplay() {
        pausePanel.setVisible(false);

        pauseButtonPanel.setVisible(true);
        pbp_pauseButton.setEnabled(true);

        gameOverPanel.setVisible(false);

        renderPanel2D.requestFocusInWindow();
    }

    /**
     * Used for showing the pausePanel as a pause indicator overlay,
     * called from Swing's EDT if the GameState is equal to GAME_PAUSE
     */
    public void showPauseMenu() {
        pausePanel.setVisible(true);

        pauseButtonPanel.setVisible(true);
        pbp_pauseButton.setEnabled(true);

        gameOverPanel.setVisible(false);
    }

    /**
     * Used for showing the gameOverPanel as a Game Over indicator overlay,
     * called from Swing's EDT if the GameState is equal to GAME_LOST
     */
    public void showGameOverMenu() {
        pausePanel.setVisible(false);
        pbp_pauseButton.setEnabled(false);

        gop_statisticsButton.setEnabled(true);

        gameOverPanel.setVisible(true);
    }
}