package game.screen;

import game.data.GameState;
import game.renderable.background.AnimatedBackground;
import game.data.GameData;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

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
        configJLabel(mp_gameLabel, gameData.getConstants().getGameName());


        mp_playButton = new JButton();
        configJButton(mp_playButton, "Play", x->{gameData.changeGameState(GameState.GAME_MAIN);});

        mp_statisticsButton = new JButton();
        configJButton(mp_statisticsButton, "Statistics", x->{gameData.changeGameState(GameState.TITLE_STATISTICS);});

        mp_settingsButton = new JButton();
        configJButton(mp_settingsButton, "Settings", x->{gameData.changeGameState(GameState.TITLE_SETTINGS);});



        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mp_gameLabel);
        mainPanel.add(Box.createVerticalGlue());

        mainPanel.add(mp_playButton);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mp_statisticsButton);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(mp_settingsButton);
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
        configJButton(sp_settingsBackButton, "Back", x->{gameData.changeGameState(GameState.TITLE_MAIN);});


        JLabel sp_settingsLabel = new JLabel();
        configJLabel(sp_settingsLabel, "Settings");


        JCheckBox sp_checkBox1 = new JCheckBox();
        configJCheckBox(sp_checkBox1, "Disable background animation", x->{gameData.setDisableBackgroundAnimation(!gameData.isBackgroundAnimationDisabled());});

        JCheckBox sp_checkBox2 = new JCheckBox();
        configJCheckBox(sp_checkBox2, "Disable player animation", x->{gameData.setPlayerAnimationDisabled(!gameData.isPlayerAnimationDisabled());});

        JCheckBox sp_checkBox3 = new JCheckBox();
        configJCheckBox(sp_checkBox3, "Disable banana rotation", x->{gameData.setCollectableRotationDisabled(!gameData.isCollectableRotationDisabled());});

        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_settingsLabel);
        settingsPanel.add(Box.createVerticalStrut(10));

        settingsPanel.add(sp_checkBox1);
        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_checkBox2);
        settingsPanel.add(Box.createVerticalGlue());
        settingsPanel.add(sp_checkBox3);

        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(sp_settingsBackButton);
        settingsPanel.add(Box.createVerticalGlue());
    }

    /**
     * Used for configuring the JLabels
     * @param label the JLabel to be configured
     * @param text text of the JLabel as a String
     */
    private void configJLabel(JLabel label, String text) {
        label.setText(text);
        label.setFont(gameData.getLabelFont());
        label.setForeground(Color.WHITE);
        //label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        //label.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
        //label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.RED, Color.BLUE));
        //label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.YELLOW, new Color(150, 90, 30)));
        label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GREEN, new Color(5, 127, 255)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Used for configuring the JButtons
     * @param button the JButton to be configured
     * @param text text of the JButton as a String
     * @param actionListener action to perform after the button press
     */
    private void configJButton(JButton button, String text, ActionListener actionListener) {
        button.setText(text);
        button.setFont(gameData.getLabelFont());
        button.setForeground(Color.BLACK);

        button.addActionListener(actionListener);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    /**
     * Used for configuring JCheckBoxes
     * @param checkBox the JCheckBox to be configured
     * @param text text of the JCheckBox as a String
     * @param actionListener action to perform after the checkBox toggle
     */
    private void configJCheckBox(JCheckBox checkBox, String text, ActionListener actionListener) {
        checkBox.setText(text);
        checkBox.setFont(gameData.getLabelFont());
        checkBox.setForeground(Color.WHITE);

        //checkBox.setSize((int) (checkBox.getWidth() * gameData.getScale()), (int) (checkBox.getHeight() * gameData.getScale()));

        checkBox.addActionListener(actionListener);

        checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        checkBox.setFocusPainted(false);
        checkBox.setOpaque(false);
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