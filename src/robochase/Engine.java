import java.util.ArrayList;

/**
 * The central class. Handles input from GUI and controls the board and objects on it.
 *
 * @author TheTechTeen
 **/

public class Engine 
{
    private int numRows;
    private int numCols;
    private ArrayList<BoardObject> entities;

    private final int stairX = 5; // TODO: Export and hardcode
    private final int stairY = 0;

    private int level;

    private final GUI gui;

    private Player human;

    /**
     * @param gui a GUI is required in order to display the board.
     */
    public Engine(GUI gui)
    {
        this.gui = gui;
    }

    /**
     * Start a new level. Creates and sets up the board.
     * @param startOver if this is true, a completely new game will be created.
     *                  otherwise the level will be incremented (the game will go onto the next, harder level)
     */
    public void startLevel(boolean startOver)
    {
        if (startOver)
        {
            level = 1;
        }
        else
        {
            level += 1;
        }
        initializeBoard(12, 12);
        placeRobots(3 + level, 0.08 + (level * 0.02)); // eventually should export this so it isn't hardcoded
        placeHuman();
        gui.displayBoard(entities, numCols, numRows);
    }

    private void initializeBoard(int numRows, int numCols)
    {
        this.numCols = numCols;
        this.numRows = numRows;

        this.entities = new ArrayList<>();

        for (int y = 0; y < numRows; y++) 
        {
            for (int x = 0; x < numCols; x++) 
            {
                if (x == stairX && y == stairY)
                {
                    entities.add(new Stair(x, y));
                }
                else if (y == 0 || y == numRows - 1 || x == 0 || x == numCols - 1)
                {
                    entities.add(new Obstacle("Fence", x, y));
                }
            }
        }
    }

    /**
     * Gets all objects at a specified x and y value
     * @return returns a list of the objects at that location
     */
    public ArrayList<BoardObject> getObjectsAt(int x, int y)
    {
        ArrayList<BoardObject> squareEntities = new ArrayList<>();

        for (BoardObject boardObject : entities) 
        {
            if (boardObject.getX() == x && boardObject.getY() == y)
            {
                squareEntities.add(boardObject);
            }
        }

        return squareEntities;
    }

    private void placeHuman()
    {
        // Place the human on an empty board space
        int humanX;
        int humanY;
        do 
        {
            humanX = 1 + (int)(Math.random() * ((numCols - 2) + 1));
            humanY = 1 + (int)(Math.random() * ((numRows - 2) + 1));
        } while (getObjectsAt(humanX, humanY).size() > 0);
        human = new Player(humanX, humanY);
        entities.add(human);
    }

    private void placeRobots(int robotCount, double efficiency)
    {
        // Place the correct number of robots on empty spaces
        int robotX;
        int robotY;

        for (int i = 0; i < robotCount; i++) 
        {
            do 
            {
                robotX = 1 + (int)(Math.random() * ((numCols - 2) + 1));
                robotY = 1 + (int)(Math.random() * ((numRows - 2) + 1));
            } while (getObjectsAt(robotX, robotY).size() > 0);
            entities.add(new Robot(robotX, robotY, efficiency));
        }
    }

    /**
     * Removes an object from the board
     */
    public void removeFromBoard(BoardObject object)
    {
        entities.remove(object);
    }

    private void checkCollisions()
    {
        for (int y = 0; y < numCols; y++)
        {
            for (int x = 0; x < numRows; x++)
            {
                ArrayList<BoardObject> objects = getObjectsAt(x, y);

                for (BoardObject object1:objects)
                {
                    for (BoardObject object2 : objects)
                    {
                        if (object1 != object2)
                        {
                            // NOTE: onCollide's boolean return indicates whether the object
                            // should be removed from the board after the collision with the other object
                            if (object1.onCollide(object2))
                            {
                                removeFromBoard(object1);
                            }
                            if (object2.onCollide(object1))
                            {
                                removeFromBoard(object2);
                            }
                            if (object1 instanceof Stair && object2 instanceof Player)
                            {
                                startLevel(false);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Moves the human and updates the world to respond to the move
     * @param direction the direction the player must move.
     *                  Must be one of the directions in the Player class.
     */
    public void humanMove(int direction)
    {
        if (human.alive)
        {
            human.move(direction);
            Robot.robotsMove(human.getX(), human.getY());
            checkCollisions();
            gui.displayBoard(entities, numCols, numRows);
        }
    }
}
