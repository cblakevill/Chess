package Model;

import javafx.geometry.Pos;

import java.util.Objects;

/**
 * Chess - Created 19/03/2018.
 */
public class Position
{
    private int file;
    private int rank;

    public Position(int file, int rank)
    {
        this.file = file;
        this.rank = rank;
    }

    public Position(Position position)
    {
        this.file = position.file;
        this.rank = position.rank;
    }

    public Position copy()
    {
        return new Position(file, rank);
    }

    public int file()
    {
        return file;
    }

    public int rank()
    {
        return rank;
    }

    public String fileStr()
    {
        return Character.toString((char)(file +96));
    }

    public String rankStr()
    {
        return Character.toString((char)(rank +48));
    }

    @Override
    public String toString()
    {
        return fileStr() + rankStr();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Position)
        {
            Position c = (Position)obj;
            return c.file == this.file && c.rank == this.rank;
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.file, this.rank);
    }
}
