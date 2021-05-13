import java.util.ArrayList;

public class Robot extends BoardObject
{
    private static final ArrayList<Robot> robots = new ArrayList<Robot>();

    public final static char SYMBOL = 'R';
    public final static String NAME = "Robot";
    public final static char SCRAP = '#';

    private boolean alive = true;
    private double efficiency;

    // TODO: Add different types of robot

    public Robot(int x, int y, double efficiency)
    {
        super(NAME + (robots.size()+1), SYMBOL, x, y);
        this.efficiency = efficiency;
        robots.add(this);
    }

    @Override
    public void onCollide (BoardObject object, Engine game)
    {
        if (object instanceof Robot)
        {
            destroy(game);
        }
        if (object instanceof Obstacle)
        {
            destroy(game);
        }
    }

    @Override
    public void destroy(Engine game)
    {
        symbol = SCRAP; // Eventually replace
        alive = false;
    }

    public void move(Engine game)
    {
        if (!alive)
        {
            return;
        }

        int playerX = game.human.getX();
        int playerY = game.human.getY();

        if (efficiency > Math.random())
        {
            return;
        }

        if (x < playerX)
        {
            x++;
        }
        else if (x > playerX)
        {
            x--;
        }
        if (y < playerY)
        {
            y++;
        }
        else if (y > playerY)
        {
            y--;
        }

        for (BoardObject collidee:game.getObjectsAt(x, y))
        {
            if(collidee != this)
            {
                onCollide(collidee, game);
                collidee.onCollide(this, game);
            }

        }
    }

    public static void robotsMove(Engine game)
    {
        for (Robot robot : robots)
        {
            robot.move(game);
        }
    }
}
