public abstract class BoardObject
{
    private String name;
    protected int x;
    protected int y;
    protected char symbol;

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

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public abstract void onCollide(BoardObject object, Engine game);

    public void destroy(Engine game)
    {
        game.removeFromBoard(this);
    }
}
