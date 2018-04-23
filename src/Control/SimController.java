package Control;

import Model.Board;
import Model.Color;
import Model.Constants;
import Model.Pieces.Piece;
import Movement.Move;
import Movement.MoveFinder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

/**
 * Chess - Created 12/04/2018.
 */
public class SimController extends GameController
{

    public SimController(Board board, MoveFinder moveFinder, Color currentPlayer, int turnCount)
    {
        super(board, moveFinder, turnCount);
        this.whitePlayer = new SimPlayer(Color.WHITE, this);
        this.blackPlayer = new SimPlayer(Color.BLACK, this);
        this.setPlayer(blackPlayer, Color.BLACK);
        this.setPlayer(whitePlayer, Color.WHITE);
        this.currentPlayer = currentPlayer == Color.WHITE ? this.whitePlayer : this.blackPlayer;
        moveFinder.setGc(this);
    }

    @Override
    public boolean executeMove(Move move)
    {
        Piece piece = move.getPiece();
        if(getMoves(piece).stream().anyMatch(mv -> mv.algebraicNotation().equals(move.algebraicNotation())))
        {
            move.execute(piece, this);

            setTurnCount(getTurnCount()+1);
            setCurrentPlayer(getCurrentPlayerAgent().getColor() == Color.WHITE ? blackPlayer : whitePlayer);
            calcIfCheck();
            move.setCheck(isCheck());
            return true;
        }
        return false;
    }

    @Override
    public void calcIfCheck()
    {
        checkMoves = new HashSet<>();
        for(Move move : getAllMoves(currentPlayer.getColor()))
        {
            if (move.isCapture() && move.getCapture().getID().equals(Constants.ID_KING))
            {
                checkMoves.add(move);
            }
        }
    }

    @Override
    public void addToMovePieceNotifier(PieceMovedObserver observer)
    {
        throw new NotImplementedException();
    }

    @Override
    public void start()
    {
        throw new NotImplementedException();
    }

    public int getMaterialScore(Color color)
    {
        Set<Piece> pieces1 = (color == Color.WHITE) ? getPieces(Color.WHITE) : getPieces(Color.BLACK);
        Set<Piece> pieces2 = (color == Color.WHITE) ? getPieces(Color.BLACK) : getPieces(Color.WHITE);
        int cost = 0;
        for(Piece piece : pieces1)
            cost += piece.materialCost();
        for(Piece piece : pieces2)
            cost -= piece.materialCost();

        return cost;
    }
}
