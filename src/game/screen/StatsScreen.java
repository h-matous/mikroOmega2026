package game.screen;

import game.data.GameData;
import game.data.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The class StatsScreen extends Screen and represents the JFrame in which the user Statistics of the Game will be displayed
 */
public class StatsScreen extends Screen {

    private DefaultListModel<String> model;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JTextField textField;

    /**
     * Constructor sets the values
     * @param gameData data of the Game
     */
    public StatsScreen(GameData gameData) {
        super(gameData);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        scrollPane = new JScrollPane(list);
        textField = new JTextField();

        for (int i = 0; i < 100; i++) {
            model.addElement(Integer.toString(i));
        }

        initialize(gameData);
    }

    /**
     * Used for initializing the StatsScreen
     * @param gameData data of the Game
     */
    private void initialize(GameData gameData) {
        this.setTitle("Statistics");

        this.setPreferredSize(gameData.getStatScreenSize());

        //This window will get closed from changing the GameState in the Game class state machine
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        this.setLayout(new BorderLayout());

        list.setBorder(BorderFactory.createLineBorder(Color.CYAN, 1, true));

        scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED, 1, false));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane, BorderLayout.CENTER);

        JButton button = new JButton("Add");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(button, BorderLayout.EAST);

        textField.setText("Type here");
        panel.add(textField, BorderLayout.CENTER);

        button.addActionListener(e -> {
            String text = textField.getText();
            if (!text.isBlank()) {
                model.addElement(text);
                textField.setText("");
            }
        });


        this.addWindowListener(new WindowAdapter() {
            /**
             * Used for also changing the GameState to TITLE_MAIN after closing the Statistics Screen
             * this JFrame will not be hidden/closed from calling the super.windowClosing(e), because
             * the default close operation is set to JFrame.DO_NOTHING_ON_CLOSE, when the GameState
             * changes, this StatScreen Screen window will be hidden with the state machine in Game class
             * @param e the event to be processed
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                gameData.changeGameState(GameState.TITLE_MAIN);
            }
        });


        this.add(panel, BorderLayout.SOUTH);

        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }
}