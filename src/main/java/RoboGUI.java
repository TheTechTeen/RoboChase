import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

public class RoboGUI extends JFrame
{
    private JPanel buttonPanel;
    private JButton buttonUp; 
    private JButton buttonDown; 
    private JButton buttonLeft; 
    private JButton buttonRight; 
    private JButton buttonStart;

    private JTextArea boardArea; 
    private JTextArea resultsArea;
    private JTextField currentScoreField;

    private int SCORE_FIELD_WIDTH = 28;
    private int RESULTS_AREA_HEIGHT = 4;
    private int RESULTS_AREA_WIDTH = 30;

    private transient Engine currentGame;

    private final char FLOOR = ' ';

    public RoboGUI()
    {
        super("RoboChase!");
        setLayout(new FlowLayout());

        boardArea = new JTextArea();
        boardArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        boardArea.setEditable(false);
        boardArea.setAlignmentX(SwingConstants.CENTER);
        add(boardArea);

        ButtonHandler handler = new ButtonHandler();
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(handler);
        buttonLeft = new JButton("Left");
        buttonLeft.addActionListener(handler);
        buttonUp = new JButton("Up");
        buttonUp.addActionListener(handler);
        buttonDown = new JButton("Down");
        buttonDown.addActionListener(handler);
        buttonRight = new JButton("Right");
        buttonRight.addActionListener(handler);

        buttonPanel = new JPanel();
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonUp);
        buttonPanel.add(buttonDown);
        buttonPanel.add(buttonRight);
        add(buttonPanel);

        resultsArea = new JTextArea();
        resultsArea.setColumns(RESULTS_AREA_WIDTH);
        resultsArea.setRows(RESULTS_AREA_HEIGHT);
        add(resultsArea);

        currentScoreField = new JTextField("", SCORE_FIELD_WIDTH);
        currentScoreField.setEditable(false);
        add(currentScoreField);

        currentGame = new Engine();
    }

    public void displayBoard()
    {
        ArrayList<BoardObject> boardObjects = currentGame.getEntities();
        int numCols = currentGame.getNumCols();
        int numRows = currentGame.getNumRows();
        char[][] board = new char[numRows][numCols];

        for (char[] row : board)
        {
            Arrays.fill(row, FLOOR);
        }

        for (BoardObject entity : boardObjects)
        {
            board[entity.getY()][entity.getX()] = entity.getSymbol();
        }

        StringBuilder boardString = new StringBuilder();

        for (char[] row : board)
        {
            for (char letter : row)
            {
                boardString.append(letter);
            }
            boardString.append("\n");
        }

        // remove the final newline
        boardString.deleteCharAt(boardString.length() - 1);

        boardArea.setText(boardString.toString());
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == buttonStart) 
            {
                currentGame = new Engine();
            }
            else if (event.getSource() == buttonLeft)
            {
                currentGame.humanMove(Player.LEFT);
            }
            else if (event.getSource() == buttonRight)
            {
                currentGame.humanMove(Player.RIGHT);
            }
            else if (event.getSource() == buttonUp)
            {
                currentGame.humanMove(Player.UP);
            }
            else if (event.getSource() == buttonDown)
            {
                currentGame.humanMove(Player.DOWN);
            }
            displayBoard();
        }
    }
}