package game;

import javax.swing.*;

/**
 * The Main class contains the Entry point of this Game
 */
public class Main {
    /**
     * The Entry point of this Game
     * @param args passed arguments as a String array
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Game game = new Game();
            game.start();
        });
    }
}