public class Engine 
{
    private BoardObject board[][];
    private RoboGUI gui;

    private final char FENCE = 'X';
    private final char STAIR = '>';
    private final char FLOOR = ' ';

    private int stairX = 5;
    private int stairY = 0;

    Engine(RoboGUI gui) 
    {
        this.gui = gui;
        startLevel(1);
    }

    public void startLevel(int level)
    {
        initializeBoard(12, 12);
        displayBoard();
    }

    public void displayBoard()
    {
        StringBuilder boardString = new StringBuilder();
        for (BoardObject[] row : board) {
            for (BoardObject object : row) {
                boardString.append(object.getSymbol());
            }
            boardString.append("\n");
        }

        // remove the final newline
        boardString.deleteCharAt(boardString.length() - 1);

        gui.displayBoard(boardString.toString());
    }

    public void initializeBoard(int numRows, int numCols)
    {
        board = new BoardObject[numRows][numCols];

        for (int y = 0; y < numRows; y++) 
        {
            for (int x = 0; x < numCols; x++) 
            {
                if (x == stairX && y == stairY)
                {
                    board[y][x] = new BoardObject("Stair", STAIR, x, y);
                }
                else if (y == 0 || y == numRows - 1 || x == 0 || x == numCols - 1)
                {
                    board[y][x] = new BoardObject("Fence", FENCE, x, y);
                }
                else
                {
                    board[y][x] = new BoardObject("Floor", FLOOR, x, y);
                }
            }
        }
    }
}
