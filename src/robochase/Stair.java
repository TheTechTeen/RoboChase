public class Stair extends BoardObject
{

    public static char SYMBOL = '<';
    public static String NAME = "Stair";

    public Stair(int x, int y)
    {
        super(NAME, SYMBOL, x, y);
    }

    @Override
    public void onCollide (BoardObject object, Engine game)
    {
        return;
    }

    @Override
    public void destroy (Engine game)
    {
        throw new RuntimeException("Stairs should NEVER be destroyed");
    }
}
