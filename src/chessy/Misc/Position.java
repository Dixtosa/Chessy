package chessy.Misc;

/**
 *
 * @author Gio Eufshi
 */



//IMPORTANT NOTE: BASIS IS LEFT DOWN
public class Position
{
    public int x, y;
    static public Position add(Position a, Position b)
    {
        return new Position(a.x+b.x, a.y+b.y);
    }
    public Position(int px, int py)
    {
        x=px;
        y=py;
    }
    public boolean equals(Position p)
    {
        return p.x==x && p.y==y;
    }
}