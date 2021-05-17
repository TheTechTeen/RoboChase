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

    protected void changeSymbol(char symbol)
    {
        this.symbol = symbol;
    }

    public abstract boolean onCollide (BoardObject object);
}
