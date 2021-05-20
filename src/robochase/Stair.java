public class Stair extends BoardObject
{

    public static char SYMBOL = '<';
    public static String NAME = "Stair";

    public Stair(int x, int y)
    {
        super(NAME, SYMBOL, x, y);
    }

    @Override
    public boolean onCollide (BoardObject object)
    {
        return false;
    }
}
