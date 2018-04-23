package Model.Pieces;

import Movement.MoveFinder;
import Model.Color;
import Model.Constants;
import Model.Position;

/**
 * Chess - Created 19/03/2018.
 */
public class Bishop extends Piece
{
    public Bishop(Position position, Color color)
    {
        super(position, color);
    }

    private Bishop(Position position, Color color, int moveCount, int hashCode)
    {
        super(position, color, moveCount, hashCode);
    }

    @Override
    public void updateMoveList(MoveFinder finder)
    {
        setMoves(finder.moveDiagonal(this, 7, getPosition(), getColor()));
    }

    @Override
    public String getID()
    {
        return Constants.ID_BISHOP;
    }

    @Override
    public String getName()
    {
        return Constants.BISHOP;
    }

    @Override
    public Piece copy()
    {
        return new Bishop(getPosition().copy(), getColor(), getMoveCount(), getHashCode());
    }

    @Override
    public int materialCost()
    {
        return 3;
    }
}
