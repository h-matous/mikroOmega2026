import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Canvas2D extends JPanel {
    private List<Sprite> sprites;

    public Canvas2D() {
        sprites = new ArrayList<>();

        sprites.add(new Sprite(null));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gfx = (Graphics2D) g;
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gfx.setColor(Color.BLUE);
        gfx.fillRect(0, 0, this.getWidth(), this.getHeight());

        gfx.setColor(Color.GREEN);
        gfx.fillRect(50, 50, 200, 150);
        gfx.setColor(Color.BLACK);
        gfx.drawString("Hello, painting!", 100, 200);


        GradientPaint gradient = new GradientPaint(70, 70, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)), 150, 150, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
        gfx.setPaint(gradient);
        gfx.fillOval(70, 70, 100, 100);

        for (Sprite s : sprites) {
            s.draw(gfx);
        }
    }


}