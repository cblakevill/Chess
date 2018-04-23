package Control.Mouse;

import Model.Pieces.Piece;
import Model.Square;

/**
 * Chess - Created 27/03/2018.
 */
public class NoPieceSelected implements MouseState
{
    @Override
    public void squareClicked(MouseHandler context, Square square)
    {

    }

    @Override
    public void pieceClicked(MouseHandler context, Piece piece)
    {
        if(context.isCurrentTurn())
        {
            if (context.getGC().getCurrentPlayer().equals(piece.getColor()))
            {
                context.setPieceSelected(piece);
                context.getDC().highlightPiece(piece);
                context.setState(new PieceSelected());
            }
        }
    }
}
