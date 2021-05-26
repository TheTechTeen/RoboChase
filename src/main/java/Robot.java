import java.util.ArrayList;

public class Robot extends BoardObject
{
    private static final ArrayList<Robot> robots = new ArrayList<>();

    public final static char SYMBOL = 'R';
    public final static String NAME = "Robot";

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
    public boolean onCollide (BoardObject object)
    {
        if (object instanceof Robot)
        {
            destroy();
            return true;
        }
        else if (object instanceof Obstacle)
        {
            destroy();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void destroy()
    {
        // TODO: add scrap on die
        alive = false;
    }

    public void move(int playerX, int playerY)
    {
        if (!alive)
        {
            return;
        }

        if (Math.random() > efficiency)
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
    }

    public static void robotsMove(int playerX, int playerY)
    {
        for (Robot robot : robots)
        {
            robot.move(playerX, playerY);
        }
    }
}
