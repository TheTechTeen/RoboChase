/**
 * The human-controlled board character.
 *
 * @author TheTechTeen
 */

public class Player extends BoardObject
{
    public final static int UP = 0;
    public final static int LEFT = 1;
    public final static int DOWN = 2;
    public final static int RIGHT = 3;

    private final static char SYMBOL = 'H';
    private final static char SMOOSHED = '%';
    private final static String NAME = "Player";

    public boolean alive;

    /**
     * Create a new player at a certain position
     *
     */
    public Player(int x, int y)
    {
        super(NAME, SYMBOL, x, y);
        alive = true;
    }

    /**
     * Move the player
     * @param direction the direction to move. One of the constants from this class
     */
    public void move(int direction)
    {
        if (!alive)
        {
            return;
        }
        // TODO: replace with a switch statement
        if (direction == UP)
        {
            y--;
        }
        else if (direction == DOWN)
        {
            y++;
        }
        else if (direction == LEFT)
        {
            x--;
        }
        else if (direction == RIGHT)
        {
            x++;
        }
        else
        {
            throw new RuntimeException("You messed up. Humans can't fly, nor move diagonally.");
        }
    }

    @Override
    public boolean onCollide (BoardObject object)
    {
        if (object instanceof Robot)
        {
            alive = false;
            changeSymbol(SMOOSHED);
        }
        else if (object instanceof Obstacle)
        {
            alive = false;
            changeSymbol(SMOOSHED);
        }
        return false;
    }
}
