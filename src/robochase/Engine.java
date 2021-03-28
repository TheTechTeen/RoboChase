import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Engine 
{
    private int numRows;
    private int numCols;
    private ArrayList<BoardObject> entities;
    private GUI gui;

    private final char FENCE = 'X';
    private final char STAIR = '>';
    private final char FLOOR = ' ';

    private int stairX = 5;
    private int stairY = 0;

    private Player human = new Player();

    Engine(GUI gui) 
    {
        this.gui = gui;
        startLevel(1);
    }

    public void startLevel(int level)
    {
        initializeBoard(12, 12);
        placeRobots(3 + level); // eventually should export this so it isn't hardcoded
        placeHuman();
        displayBoard();
    }

    public void displayBoard()
    {
        char[][] board = new char[numRows][numCols];
        
        for (char[] row : board) 
        {
            Arrays.fill(row, ' ');
        }
        
        for (BoardObject entity : entities) 
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

        gui.displayBoard(boardString.toString());
    }

    public void initializeBoard(int numRows, int numCols)
    {
        this.numCols = numCols;
        this.numRows = numRows;

        this.entities = new ArrayList<BoardObject>();

        for (int y = 0; y < numRows; y++) 
        {
            for (int x = 0; x < numCols; x++) 
            {
                if (x == stairX && y == stairY)
                {
                    entities.add(new BoardObject("Stair", STAIR, x, y));
                }
                else if (y == 0 || y == numRows - 1 || x == 0 || x == numCols - 1)
                {
                    entities.add(new BoardObject("Fence", FENCE, x, y));
                }
            }
        }
    }

    public ArrayList<BoardObject> getObjectsAt(int x, int y)
    {
        ArrayList<BoardObject> squareEntities = new ArrayList<BoardObject>();

        for (BoardObject boardObject : entities) 
        {
            if (boardObject.getX() == x && boardObject.getY() == y)
            {
                squareEntities.add(boardObject);
            }
        }

        return squareEntities;
    }

    public void placeHuman()
    {
        // Place the human on an empty board space
        int humanX;
        int humanY;
        do 
        {
            humanX = (int) 1 + (int)(Math.random() * ((numCols - 2) + 1));
            humanY = (int) 1 + (int)(Math.random() * ((numRows - 2) + 1));
        } while (getObjectsAt(humanX, humanY).size() > 0);
        human.setX(humanX);
        human.setY(humanY);
        entities.add(human);
    }

    public void placeRobots(int robotCount)
    {
        // Place the correct number of robots on empty spaces
        int robotX;
        int robotY;

        for (int i = 0; i < robotCount; i++) 
        {
            do 
            {
                robotX = (int) 1 + (int)(Math.random() * ((numCols - 2) + 1));
                robotY = (int) 1 + (int)(Math.random() * ((numRows - 2) + 1));
            } while (getObjectsAt(robotX, robotY).size() > 0);
            entities.add(new BoardObject("Robot", 'R', robotX, robotY));
        }
    }
}
