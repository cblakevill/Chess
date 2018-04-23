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
public class Knight extends Piece
{
    public Knight(Position position, Color color)
    {
        super(position, color);
    }

    private Knight(Position position, Color color, int moveCount, int hashCode)
    {
        super(position, color, moveCount, hashCode);
    }

    @Override
    public void updateMoveList(MoveFinder finder)
    {
        Set<Move> list = new HashSet<>();
        int[][] moves = {{1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}, {-2,-1}, {-2,1}, {-1,2}};
        for(int[] possibleMoves : moves)
        {
            Move move = finder.validateMove(this, getColor(), getPosition(), possibleMoves[0], possibleMoves[1]);
            if(move != null)
                list.add(move);
        }
        setMoves(list);
    }

    @Override
    public String getID()
    {
        return Constants.ID_KNIGHT;
    }

    @Override
    public String getName()
    {
        return Constants.KNIGHT;
    }

    @Override
    public Piece copy()
    {
        return new Knight(new Position(getPosition()), getColor(), getMoveCount(), getHashCode());
    }

    @Override
    public int materialCost()
    {
        return 3;
    }
}
