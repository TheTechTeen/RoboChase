public class Player extends BoardObject 
{
    public final static int UP = 0;
    public final static int LEFT = 1;
    public final static int DOWN = 2;
    public final static int RIGHT = 3;

    public final static char SYMBOL = 'H';
    public final static char SMOOSHED = '%';
    public final static String NAME = "Player";

    public boolean alive;

    public Player(int x, int y)
    {
        super(NAME, SYMBOL, x, y);
        alive = true;
    }

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
