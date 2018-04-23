package Control.Mouse;

import Movement.Move;
import Model.Pieces.Piece;
import Model.Square;

/**
 * Chess - Created 27/03/2018.
 */
public class PieceSelected implements MouseState
{
    @Override
    public void squareClicked(MouseHandler context, Square square)
    {
        if(context.isCurrentTurn())
        {
            if (context.getGC().getCurrentPlayer() == context.getPieceSelected().getColor())
            {
                context.getDC().unhighlightPiece(context.getPieceSelected());

                for (Move move : context.getGC().getMoves(context.getPieceSelected()))
                {
                    if (move.getTo().equals(square.getPosition()))
                    {
                        context.setCurrentTurn(false);
                        context.setMove(move);
                        synchronized (context.getLock())
                        {
                            context.getLock().unlock();
                        }
                        break;
                    }
                }
                context.setState(new NoPieceSelected());
            }
        }


    }

    @Override
    public void pieceClicked(MouseHandler context, Piece piece)
    {
        if (context.isCurrentTurn())
        {
            context.getDC().unhighlightPiece(context.getPieceSelected());
            for (Move move : context.getGC().getMoves(context.getPieceSelected()))
            {
                if (move.getTo().equals(piece.getPosition()))
                {
                    context.setCurrentTurn(false);
                    context.setMove(move);
                    context.setState(new NoPieceSelected());
                    synchronized (context.getLock())
                    {
                        context.getLock().unlock();
                    }
                    return;
                }
            }

            if (context.getGC().getCurrentPlayer() == piece.getColor())
            {
                context.setPieceSelected(piece);
                context.getDC().highlightPiece(piece);
            }
        }


    }
}
