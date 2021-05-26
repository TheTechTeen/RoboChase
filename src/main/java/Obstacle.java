/**
 * A stationary, normally immovable object that destroys any object that runs into it.
 * Normally a fence, wall, or pillar
 *
 * @author TheTechTeen
 */
public class Obstacle extends BoardObject
{
    public static char SYMBOL = 'X';
    public Obstacle(String name, int x, int y)
    {
        super(name, SYMBOL, x, y);
    }

    @Override
    public boolean onCollide (BoardObject object)
    {
        return false;
    }
}
