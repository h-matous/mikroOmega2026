public class Game {
    private TitleScreen titleScreen;
    private GameSceen gameSceen;

    //private VisibilityController[] screens;

    public Game() {
        titleScreen = new TitleScreen();
        gameSceen = new GameSceen();

        //screens = new VisibilityController[]{titleScreen, gameSceen};

    }

    public void start() {
        //screens[0].show();
        titleScreen.setVisible(true);
        gameSceen.setVisible(true);
    }

    public void showGame() {
        gameSceen.setVisible(true);
        //titleScreen.setVisible(false);
    }
}
