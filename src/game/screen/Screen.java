package game.screen;

import game.data.GameData;

import javax.swing.*;

public class Screen extends JFrame implements VisibilityController {
    protected final GameData gameData;


    public Screen(GameData gameData) {
        this.gameData = gameData;
    }
}
