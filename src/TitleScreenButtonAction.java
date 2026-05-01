import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreenButtonAction implements ActionListener {
    private Game game;

    public TitleScreenButtonAction(Game game) {
        super();

        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked");
        game.showGame();
    }
}
