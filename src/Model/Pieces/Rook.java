package Model.Pieces;

import Movement.MoveFinder;
import Model.Color;
import Model.Constants;
import Model.Position;

/**
 * Chess - Created 19/03/2018.
 */
public class Rook extends Piece
{
    public Rook(Position position, Color color)
    {
        super(position, color);
    }

    private Rook(Position position, Color color, int moveCount, int hashCode)
    {
        super(position, color, moveCount, hashCode);
    }

    @Override
    public void updateMoveList(MoveFinder finder)
    {
        setMoves(finder.moveStraight(this, 7, getPosition(), getColor()));
    }

    @Override
    public String getID()
    {
        return Constants.ID_ROOK;
    }

    @Override
    public String getName()
    {
        return Constants.ROOK;
    }

    @Override
    public Piece copy()
    {
        return new Rook(getPosition().copy(), getColor(), getMoveCount(), getHashCode());
    }

    @Override
    public int materialCost()
    {
        return 3;
    }
}
