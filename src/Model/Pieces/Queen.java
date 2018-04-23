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
public class Queen extends Piece
{
    public Queen(Position position, Color color)
    {
        super(position, color);
    }

    private Queen(Position position, Color color, int moveCount, int hashCode)
    {
        super(position, color, moveCount, hashCode);
    }

    @Override
    public void updateMoveList(MoveFinder finder)
    {
        Set<Move> list = new HashSet<>();
        list.addAll(finder.moveDiagonal(this, 7, getPosition(), getColor()));
        list.addAll(finder.moveStraight(this, 7, getPosition(), getColor()));
        setMoves(list);
    }

    @Override
    public String getID()
    {
        return Constants.ID_QUEEN;
    }

    @Override
    public String getName()
    {
        return Constants.QUEEN;
    }

    @Override
    public Piece copy()
    {
        return new Queen(new Position(getPosition()), getColor(), getMoveCount(), getHashCode());
    }

    @Override
    public int materialCost()
    {
        return 9;
    }
}
