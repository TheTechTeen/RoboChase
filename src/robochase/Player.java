public class Player extends BoardObject 
{
    public final static int UP = 0;
    public final static int LEFT = 1;
    public final static int DOWN = 2;
    public final static int RIGHT = 3;

    public Player()
    {
        super("Player", 'H', 0, 0);
    }

    public void move(int direction)
    {
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
}
