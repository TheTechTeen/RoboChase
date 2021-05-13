public class Obstacle extends BoardObject
{
    public static char SYMBOL = 'X';
    public Obstacle(String name, int x, int y)
    {
        super(name, SYMBOL, x, y);
    }

    @Override
    public void onCollide (BoardObject object, Engine game)
    {
        return;
    }
}
