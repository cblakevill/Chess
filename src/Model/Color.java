package Model;

/**
 * Chess - Created 12/04/2018.
 */
public enum Color
{
    BLACK,
    WHITE;

    @Override
    public String toString()
    {
        switch (this)
        {
            case BLACK:
                return "black";
            case WHITE:
                return "white";
            default:
                return "";
        }
    }

    public Color opposite()
    {
        return this == WHITE ? BLACK : WHITE;
    }
}
