package game.screen;

import game.data.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Screen class is a basic window abstraction over JFrame that also holds gameData
 */
public class Screen extends JFrame {
    protected final GameData gameData;

    /**
     * Constructor sets gameData
     * @param gameData data needed for the Game
     */
    public Screen(GameData gameData) {
        this.gameData = gameData;
    }

    /**
     * Used for configuring the JLabels
     * @param gameData data of the Game
     * @param label the JLabel to be configured
     * @param text text of the JLabel as a String
     */
    public static void configJLabel(GameData gameData, JLabel label, String text) {
        label.setText(text);
        label.setFont(gameData.getLabelFont());
        label.setForeground(Color.WHITE);
        //label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        //label.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
        //label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.RED, Color.BLUE));
        //label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.YELLOW, new Color(150, 90, 30)));
        //label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GREEN, new Color(5, 127, 255)));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Used for configuring the JButtons
     * @param gameData data of the Game
     * @param button the JButton to be configured
     * @param text text of the JButton as a String
     * @param actionListener action to perform after the button press
     */
    public static void configJButton(GameData gameData, JButton button, String text, ActionListener actionListener) {
        button.setText(text);
        button.setFont(gameData.getLabelFont());
        button.setForeground(Color.BLACK);

        button.addActionListener(actionListener);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    /**
     * Used for configuring the JTextFields
     * @param gameData data of the Game
     * @param textField the JTextField to be configured
     * @param text initial text of the JTextField as a String
     * @param actionListener action to perform after the button press
     */
    public static void configJTextField(GameData gameData, JTextField textField, String text, ActionListener actionListener) {
        textField.setText(text);
        textField.setFont(gameData.getLabelFont());
        textField.setForeground(Color.WHITE);
        textField.addActionListener(actionListener);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField.setOpaque(false);
    }

    /**
     * Used for configuring JCheckBoxes
     * @param gameData data of the Game
     * @param checkBox the JCheckBox to be configured
     * @param text text of the JCheckBox as a String
     * @param actionListener action to perform after the checkBox toggle
     */
    public static void configJCheckBox(GameData gameData, JCheckBox checkBox, String text, ActionListener actionListener) {
        checkBox.setText(text);
        checkBox.setFont(gameData.getLabelFont());
        checkBox.setForeground(Color.WHITE);

        //checkBox.setSize((int) (checkBox.getWidth() * gameData.getScale()), (int) (checkBox.getHeight() * gameData.getScale()));

        checkBox.addActionListener(actionListener);

        checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        checkBox.setFocusPainted(false);
        checkBox.setOpaque(false);
    }
}