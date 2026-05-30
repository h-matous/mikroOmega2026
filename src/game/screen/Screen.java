package game.screen;

import game.data.GameData;

import javax.swing.*;

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
}