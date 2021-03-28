public class Player extends BoardObject 
{
    public int UP = 0;
    public int LEFT = 1;
    public int DOWN = 2;
    public int RIGHT = 3;

    public Player()
    {
        super("Player", 'H', 0, 0);
    }
}
