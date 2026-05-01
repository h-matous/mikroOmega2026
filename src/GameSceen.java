import javax.swing.*;
import java.awt.*;

public class GameSceen extends JFrame implements VisibilityController {
    private final Dimension frameDimension;

    public GameSceen() {
        super();

        //1920 * 0,25, 1080 * 0,8
        frameDimension = new Dimension(480, 864);

        initialize();
    }

    private void initialize() {
        Canvas2D canvas2D = new Canvas2D();

        this.setContentPane(canvas2D);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Hra");
        this.setSize(frameDimension);

        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.setVisible(false);
    }

}