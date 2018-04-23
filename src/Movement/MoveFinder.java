package Movement;

import Control.GameController;
import Control.SimController;
import Model.Board;
import Model.Color;
import Model.Constants;
import Model.Pieces.*;
import Model.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Chess - Created 19/03/2018.
 */
public class MoveFinder
{
    private Board board;

    public MoveFinder(Board board)
    {
        this.board = board;
    }

    public Move validateMove(Piece piece, Color color, Position curPos, int moveByX, int moveByY)
    {
        Position pos = new Position(curPos.file() + moveByX, curPos.rank() + moveByY);
        if(pos.file() >= 1 && pos.file() <= 8 && pos.rank() >= 1 && pos.rank() <= 8)
        {
            if (board.getSquare(pos).isEmpty())
            {
                return new Move(piece, curPos, pos);
            }
            if(enemyPiece(pos, color) && !(piece instanceof Pawn))
            {
                return new Move(piece, curPos, pos, board.getSquare(pos).getPiece());
            }
        }
        return null;
    }

    public Set<Move> moveForward(Piece piece, int limit, Position pos, Color color)
    {
        Set<Move> set = new HashSet<>();
        moveRecurse(piece, set, 1, limit, pos, color, 0, dyForward(color));
        return set;
    }

    public Set<Move> moveStraight(Piece piece, int limit, Position currPos, Color color)
    {
        Set<Move> set = new HashSet<>();
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, 1, 0));
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, -1, 0));
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, 0, 1));
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, 0, -1));
        return set;
    }

    public Set<Move> moveDiagonal(Piece piece, int limit, Position currPos, Color color)
    {
        Set<Move> set = new HashSet<>();
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, 1, 1));
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, 1, -1));
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, -1, 1));
        set.addAll(moveRecurse(piece, set, 1, limit, currPos, color, -1, -1));
        return set;
    }

    private Set<Move> moveRecurse(Piece piece, Set<Move> set, int currentStep, int stepLimit, Position currPos, Color color, int x, int y)
    {
        Move newMove = validateMove(piece, color, currPos, currentStep*x, currentStep*y);
        if(currentStep <= stepLimit && newMove != null)
        {
            Position newPos = newMove.getTo();
            currentStep++;
            set.add(newMove);
            if(!board.getSquare(newPos).isEmpty() && enemyPiece(newPos, color))
                return set;

            moveRecurse(piece, set, currentStep, stepLimit, currPos, color, x, y);
        }
        return set;
    }

    public Set<Move> pawnUniqueMoves(Piece piece, Position pos, Color color, int moveCount)
    {
        Set<Move> set = new HashSet<>();
        int dy = dyForward(color);

        //capture
        int[] dxs = {-1, 1};
        for(int dx : dxs)
        {
            //Move move = validateMove(piece, color, pos, dx, dy);
            Position newPos = new Position(pos.file() + dx, pos.rank() + dy);
            if(newPos.file() >= 1 && newPos.file() <= 8 && newPos.rank() >= 1 && newPos.rank() <= 8)
            {
                if(!board.getSquare(newPos).isEmpty() && enemyPiece(newPos, color))
                {
                    set.add(new Move(piece, pos, newPos, board.getSquare(newPos).getPiece()));
                }
            }
        }

        //TODO: En passant
        {

        }

        //first move
        if(moveCount == -1)
        {
            set.addAll(moveForward(piece, 2, pos, color));
            Position c = new Position(pos.file(), pos.rank() + dyForward(color));
        }

        return set;
    }

    private int dyForward(Color color)
    {
        return color == Color.WHITE ? 1 : -1;
    }

    private boolean enemyPiece(Position pos, Color color)
    {
        return board.getSquare(pos).getPiece().getColor() != color;
    }

    public GameController gc;

    public void setGc(GameController gc)
    {
        this.gc = gc;
    }

    public Set<Move> checkMoves(Set<Move> moves, Color color)
    {
        SimController gc = this.gc.getSim(color);
        Set<Move> allowedMoves = new HashSet<>();
        for(Move moved : moves)
        {
            gc.executeMove(moved);
            if(gc.checkMoves.size() == 0)
            {
                allowedMoves.add(moved);
            }
            gc.undoMove();
        }
        return allowedMoves;
    }

    //TODO: Castling rights/moves
}
