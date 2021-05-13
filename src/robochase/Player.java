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

    public void move(int direction, Engine game)
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
        for (BoardObject collidee:game.getObjectsAt(x, y))
        {
            onCollide(collidee, game);
            collidee.onCollide(this, game);
        }
    }

    @Override
    public void onCollide (BoardObject object, Engine game)
    {
        if (object instanceof Robot)
        {
            alive = false;
            symbol = SMOOSHED;
        }
        else if (object instanceof Obstacle)
        {
            alive = false;
            symbol = SMOOSHED;
        }
        else if (object instanceof Stair)
        {
            game.startLevel(false);
        }
    }
}
