package game.screen;

import game.data.GameState;
import game.data.InputMethod;
import game.renderable.background.AnimatedBackground;
import game.data.GameData;

import javax.swing.*;

import java.awt.*;


/**
 * The class TitleScreen extends RenderScreen and represents the first JFrame which will be shown to the Player
 */
public class TitleScreen extends RenderScreen {

    JPanel mainPanel = new JPanel();

    JPanel settingsPanel = new JPanel() {
        /**
         * Needed for drawing the overlay over the mainPanel,
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

    JButton mp_playButton;
    JButton mp_statisticsButton;
    JButton mp_settingsButton;
    JButton mp_exitButton;

    /**
     * Constructor sets the values
     * @param gameData data of the Game
     */
    public TitleScreen(GameData gameData) {
        super(gameData, new RenderPanel2D(gameData, gameData.getTitleScreenSize(), new AnimatedBackground(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getTitleScreenSize()), true));

        initialize(gameData);
    }


    /**
     * Used for initializing the TitleScreen
     * @param gameData data of the Game
     */
    private void initialize(GameData gameData) {
        initMainPanel();
        initSettingsPanel();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(gameData.getTitleScreenSize());
        layeredPane.setBounds(0, 0, gameData.getTitleScreenSize().width, gameData.getTitleScreenSize().height);
        layeredPane.setOpaque(false);


        layeredPane.add(mainPanel, Integer.valueOf(0));
        layeredPane.add(settingsPanel, Integer.valueOf(1));

        mainPanel.setVisible(false);
        settingsPanel.setVisible(false);


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
     * Used for initializing the mainPanel of the TitleScreen
     */
    private void initMainPanel() {
        //Main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBounds(0, 0, gameData.getTitleScreenSize().width, gameData.getTitleScreenSize().height);
        mainPanel.setOpaque(false);

        JLabel mp_gameLabel = new JLabel();
        configJLabel(gameData, mp_gameLabel, gameData.getConstants().getGameName());


        mp_playButton = new JButton();
        configJButton(gameData, mp_playButton, "Play", x->{gameData.changeGameState(GameState.GAME_MAIN);});

        mp_statisticsButton = new JButton();
        configJButton(gameData, mp_statisticsButton, "Statistics", x->{gameData.changeGameState(GameState.TITLE_STATISTICS);});

        mp_settingsButton = new JButton();
        configJButton(gameData, mp_settingsButton, "Settings", x->{gameData.changeGameState(GameState.TITLE_SETTINGS);});

        mp_exitButton = new JButton();
        configJButton(gameData, mp_exitButton, "Exit", x->{gameData.changeGameState(GameState.EXIT);});


        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mp_gameLabel);
        mainPanel.add(Box.createVerticalStrut(150));

        mainPanel.add(mp_playButton);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mp_statisticsButton);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mp_settingsButton);

        mainPanel.add(Box.createVerticalStrut(150));
        mainPanel.add(mp_exitButton);

        mainPanel.add(Box.createVerticalGlue());
    }

    /**
     * Used for initializing the settingsPanel of the TitleScreen
     */
    private void initSettingsPanel() {
        //Settings panel
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBounds(0, 0, gameData.getTitleScreenSize().width, gameData.getTitleScreenSize().height);
        settingsPanel.setOpaque(false);

        JButton sp_settingsBackButton = new JButton();
        configJButton(gameData, sp_settingsBackButton, "Back", x->{gameData.changeGameState(GameState.TITLE_MAIN);});


        JLabel sp_settingsLabel = new JLabel();
        configJLabel(gameData, sp_settingsLabel, "Settings");


        JLabel sp_inputMethodLabel = new JLabel();
        configJLabel(gameData, sp_inputMethodLabel, "Choose your input method:");

        JComboBox<String> sp_comboBox = new JComboBox<>(InputMethod.getValueNames());

        sp_comboBox.setMaximumSize(new Dimension(settingsPanel.getWidth() / 3, settingsPanel.getHeight() / 3));
        sp_comboBox.addActionListener(x->{gameData.setChosenInputMethod(InputMethod.values()[sp_comboBox.getSelectedIndex()]);});

        sp_comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);


        JCheckBox sp_checkBox1 = new JCheckBox();
        configJCheckBox(gameData, sp_checkBox1, "Disable background animation", x->{gameData.setDisableBackgroundAnimation(!gameData.isBackgroundAnimationDisabled());});

        JCheckBox sp_checkBox2 = new JCheckBox();
        configJCheckBox(gameData, sp_checkBox2, "Disable player animation", x->{gameData.setPlayerAnimationDisabled(!gameData.isPlayerAnimationDisabled());});

        JCheckBox sp_checkBox3 = new JCheckBox();
        configJCheckBox(gameData, sp_checkBox3, "Disable banana rotation", x->{gameData.setCollectableRotationDisabled(!gameData.isCollectableRotationDisabled());});

        JCheckBox sp_checkBox4 = new JCheckBox();
        configJCheckBox(gameData, sp_checkBox4, "Disable particles", x->{gameData.setDisableParticles(!gameData.isParticlesDisabled());});

        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_settingsLabel);
        settingsPanel.add(Box.createVerticalStrut(125));

        settingsPanel.add(sp_inputMethodLabel);
        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_comboBox);
        settingsPanel.add(Box.createVerticalStrut(75));

        settingsPanel.add(sp_checkBox1);
        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_checkBox2);
        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_checkBox3);
        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_checkBox4);

        settingsPanel.add(Box.createVerticalStrut(125));
        settingsPanel.add(sp_settingsBackButton);
        settingsPanel.add(Box.createVerticalGlue());
    }


    /**
     * Used for enabling or disabling all JButtons on the mainPanel,
     * called from the Game class when the GameState changes and an
     * overlay is expected to cover the mainPanel
     * @param condition the new JButton enabled/disabled state as a boolean
     */
    public void setEnabledForMainPanelButtons(boolean condition) {
        mp_playButton.setEnabled(condition);
        mp_statisticsButton.setEnabled(condition);
        mp_settingsButton.setEnabled(condition);
        mp_exitButton.setEnabled(condition);
    }

    /**
     * Used for changing the visibility of the mainPanel,
     * called from the Game class when the GameState changes
     * @param condition the new visibility as a boolean
     */
    public void setMainPanelVisibility(boolean condition) {
        mainPanel.setVisible(condition);
    }

    /**
     * Used for changing the visibility of the settingsPanel,
     * called from the Game class when the GameState changes
     * @param condition the new visibility as a boolean
     */
    public void setSettingsPanelVisibility(boolean condition) {
        settingsPanel.setVisible(condition);
    }
}