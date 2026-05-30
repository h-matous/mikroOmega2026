package game.screen;

import game.data.GameData;
import game.data.GameState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The class StatsScreen extends Screen and represents the JFrame in which the user Statistics of the Game will be displayed
 */
public class StatsScreen extends Screen {

    private JTable table;
    private JScrollPane scrollPane;

    private JPanel statPanel;

    /**
     * Constructor sets the values
     * @param gameData data of the Game
     */
    public StatsScreen(GameData gameData) {
        super(gameData);

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


        //JTable with the Statistics database
        table = new JTable();
        reloadTable();

        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setRowHeight(20);

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        table.setDefaultEditor(Object.class, null);

        JTableHeader tableHeader = table.getTableHeader();

        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        tableHeader.setFont(gameData.getLabelFont());


        //JScrollPane holds the JTable
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Statistics JPanel that holds everything
        statPanel = new JPanel(new BorderLayout());
        statPanel.add(scrollPane, BorderLayout.CENTER);


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


        this.add(statPanel, BorderLayout.CENTER);


        this.setResizable(false);
        this.pack();

        this.setIconImage(gameData.getTexMngr().getTexture("icon").getImage());

        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }


    /**
     * Used for reloading the Statistics database data set
     * called
     */
    public void reloadTable() {
        final String[] columnNames = {"Name", "Score", "Input", "Date", "Time"};
        table.setModel(new DefaultTableModel(gameData.getStatManager().toObject2DArray(), columnNames) {
            /*
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            */
        });
    }
}