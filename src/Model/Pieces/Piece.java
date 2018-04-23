package Model.Pieces;

import Movement.MoveFinder;
import Model.Color;
import Movement.Move;
import Model.Position;

import java.util.*;

/**
 * Chess - Created 19/03/2018.
 */
public abstract class Piece
{
    private Position position;
    private final Color color;
    private Set<Move> moves;
    private int moveCount;
    private int lastUpdated;
    private int hashCode;

    public Piece(Position position, Color color)
    {
        this.position = position;
        this.color = color;
        this.moves = new HashSet<>();
        this.moveCount = -1;
        this.lastUpdated = -1;
        this.hashCode = Objects.hash(position, color, getName());
    }

    protected Piece(Position position, Color color, int moveCount, int hashCode)
    {
        this.position = position;
        this.color = color;
        this.moves = new HashSet<>();
        this.moveCount = moveCount;
        this.lastUpdated = -1;
        this.hashCode = hashCode;
    }

    protected abstract void updateMoveList(MoveFinder finder);

    public abstract String getID();
    public abstract String getName();

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Color getColor()
    {
        return color;
    }

    public Set<Move> getMoves(MoveFinder finder, int turnCount, Position kingPos)
    {
        if (lastUpdated < turnCount)
        {
            updateMoveList(finder);
            lastUpdated = turnCount;
        }
        return moves;
    }

    public void setMoves(Set<Move> moves)
    {
        this.moves = moves;
    }

    public int getMoveCount()
    {
        return moveCount;
    }

    public void incrementMoveCount()
    {
        moveCount++;
    }

    public void decrementMoveCount()
    {
        moveCount--;
    }

    public abstract Piece copy();

    public abstract int materialCost();

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Piece)
        {
            Piece piece = (Piece)obj;
            return this.getID().equals(piece.getID()) && this.position.equals(piece.position) && this.color == piece.color;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return hashCode;
    }

    public int getHashCode()
    {
        return hashCode;
    }
}
