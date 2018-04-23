package Model.Pieces;

import Movement.MoveFinder;
import Model.Color;
import Model.Constants;
import Movement.Move;
import Model.Position;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Chess - Created 19/03/2018.
 */
public class Pawn extends Piece
{
    public Pawn(Position position, Color color)
    {
        super(position, color);
    }

    private Pawn(Position position, Color color, int moveCount, int hashCode)
    {
        super(position, color, moveCount, hashCode);
    }

    @Override
    public void updateMoveList(MoveFinder finder)
    {
        Set<Move> list = new HashSet<>();
        list.addAll(finder.pawnUniqueMoves(this, getPosition(), getColor(), getMoveCount()));
        list.addAll(finder.moveForward(this, 1, getPosition(), getColor()));
        setMoves(list);
    }

    @Override
    public String getID()
    {
        return Constants.ID_PAWN;
    }

    @Override
    public String getName()
    {
        return Constants.PAWN;
    }

    @Override
    public Piece copy()
    {
        return new Pawn(getPosition().copy(), getColor(), getMoveCount(), getHashCode());
    }

    @Override
    public int materialCost()
    {
        return 1;
    }
}
