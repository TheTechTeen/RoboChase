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

public class RoboGUI extends JFrame implements GUI
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

    private Engine currentGame;

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

        start();
    }

    public void start()
    {
        currentGame = new Engine(RoboGUI.this);
    }

    public void displayBoard(String boardString)
    {
        boardArea.setText(boardString);
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == buttonStart) {
                currentGame.startLevel(1);
            }
        }
    }
}