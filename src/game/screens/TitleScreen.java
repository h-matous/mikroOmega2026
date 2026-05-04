package game.screens;

import game.Game;
import game.VisibilityController;

import javax.swing.*;
import java.awt.*;


public class TitleScreen extends JFrame implements VisibilityController {
    private final Dimension frameDimension;

    private Game game;

    public TitleScreen(Game game) {
        super();

        this.game = game;


        frameDimension = new Dimension(800, 600);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        //this.getGraphicsConfiguration().getDevice().getDisplayMode();

        initialize();
    }


    public void initialize() {
        Panel panel = new Panel();

        panel.setLayout(new BorderLayout());

        JButton b1 = new JButton("Ahojky kamarádíčku");
        b1.setFocusPainted(false);
        JLabel l1 = new JLabel("cauky mnauky1");
        JLabel l2 = new JLabel("cauky mnauky2");
        JLabel l3 = new JLabel("cauky mnauky3");
        JLabel l4 = new JLabel("cauky mnauky4");


        l1.setHorizontalAlignment(JLabel.CENTER);
        l3.setHorizontalAlignment(JLabel.CENTER);

        panel.add(l1, BorderLayout.NORTH);
        panel.add(l2, BorderLayout.WEST);
        panel.add(l3, BorderLayout.CENTER);
        panel.add(l4, BorderLayout.EAST);

        panel.add(b1, BorderLayout.SOUTH);


        ImageIcon kyticka = new ImageIcon(getClass().getResource("/kyticka.png"));
        panel.add(new JLabel(kyticka, JLabel.CENTER), BorderLayout.CENTER);


        b1.addActionListener(x->game.showGame());

        this.add(panel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Okýnko");
        this.setPreferredSize(frameDimension);

        this.pack();

        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setVisible(false);
    }
}
