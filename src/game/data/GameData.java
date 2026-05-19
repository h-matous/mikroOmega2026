package game.data;

import game.KeyHandler;
import game.TextureManager;

import java.awt.*;
import java.util.Random;

public class GameData {
    private final GameConstants gameConstants;

    private final Dimension screenSize;
    private final Dimension gameScreenSize;
    private final Dimension titleScreenSize;

    private final TextureManager texMngr;

    private final KeyHandler keyHandler;


    private final Random rnd;


    public GameData() {
        this.gameConstants = new GameConstants();

        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        this.screenSize = new Dimension(dm.getWidth(), dm.getHeight());

        this.gameScreenSize = new Dimension((int) (screenSize.width * gameConstants.getGameScreenSizePercentage().getX()), (int) (screenSize.height * gameConstants.getGameScreenSizePercentage().getY()));
        this.titleScreenSize = new Dimension((int) (screenSize.width * gameConstants.getTitleScreenSizePercentage().getX()), (int) (screenSize.height * gameConstants.getTitleScreenSizePercentage().getY()));

        this.texMngr = new TextureManager(gameConstants);

        this.keyHandler = new KeyHandler();

        this.rnd = new Random();
    }

    public GameConstants getConstants() {
        return gameConstants;
    }

    public Dimension getGameScreenSize() {
        return gameScreenSize;
    }

    public Dimension getTitleScreenSize() {
        return titleScreenSize;
    }

    public TextureManager getTexMngr() {
        return texMngr;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public Random getRnd() {
        return rnd;
    }
}
