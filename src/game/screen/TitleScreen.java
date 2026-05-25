package game.screen;

import game.Game;
import game.renderable.background.AnimatedBackground;
import game.data.GameData;

import javax.swing.*;
import java.awt.*;


public class TitleScreen extends RenderScreen {

    public TitleScreen(Game game, GameData gameData) {
        super(gameData, new RenderPanel2D(gameData, gameData.getTitleScreenSize(), new AnimatedBackground(gameData, gameData.getConstants().getAnimatedBackgroundData(), gameData.getTitleScreenSize())));

        initialize(game, gameData);
    }


    public void initialize(Game game, GameData gameData) {

        renderPanel2D.setLayout(new BorderLayout());

        JButton b1 = new JButton("Ahojky kamarádíčku");
        b1.setFocusPainted(false);
        b1.setFont(gameData.getLabelFont());
        JLabel l1 = new JLabel("cauky mnauky1");
        JLabel l2 = new JLabel("cauky mnauky2");
        JLabel l3 = new JLabel("cauky mnauky3");
        JLabel l4 = new JLabel("cauky mnauky4");

        l1.setFont(gameData.getLabelFont());
        l2.setFont(gameData.getLabelFont());
        l3.setFont(gameData.getLabelFont());
        l4.setFont(gameData.getLabelFont());

        l1.setHorizontalAlignment(JLabel.CENTER);
        l3.setHorizontalAlignment(JLabel.CENTER);

        renderPanel2D.add(l1, BorderLayout.NORTH);
        renderPanel2D.add(l2, BorderLayout.WEST);
        renderPanel2D.add(l3, BorderLayout.CENTER);
        renderPanel2D.add(l4, BorderLayout.EAST);

        renderPanel2D.add(b1, BorderLayout.SOUTH);


        ImageIcon kyticka = new ImageIcon(getClass().getResource("/kyticka.png"));
        renderPanel2D.add(new JLabel(kyticka, JLabel.CENTER), BorderLayout.CENTER);


        b1.addActionListener(x->game.showGame());

        //this.add(renderCanvas2D);
        this.setContentPane(renderPanel2D);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Monkey Banana Catch!");
        this.setPreferredSize(gameData.getTitleScreenSize());

        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }
}