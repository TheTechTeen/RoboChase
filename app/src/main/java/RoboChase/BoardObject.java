/**
 * Abstract class holding the information every board object must have.
 * @author TheTechTeen
 */
public abstract class BoardObject
{
    private String name;
    protected int x;
    protected int y;
    private char symbol;

    BoardObject(String name, char symbol, int x, int y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public String getName()
    {
        return name;
    }

    public char getSymbol()
    {
        return symbol;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    /**
     * Change the symbol for the object (currently how it is rendered on the Swing TextArea GUI)
     * @param symbol The new symbol that the object will appear as from now on.
     */
    protected void changeSymbol(char symbol)
    {
        this.symbol = symbol;
    }

    /**
     * @deprecated
     * Change the name of the object.
     * @param name The new symbol that the object will appear as from now on.
     */
    protected void changeName(String name)
    {
        this.name = name;
    }

    /**
     * Handles collisions between 2 or more objects
     * @param object the object this object collided with.
     * @return returns true if this object should be removed from the board.
     */
    public abstract boolean onCollide (BoardObject object);
}
