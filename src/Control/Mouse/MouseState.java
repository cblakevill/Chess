package Control.Mouse;

import Model.Pieces.Piece;
import Model.Square;

/**
 * Chess - Created 27/03/2018.
 */
public interface MouseState
{
    void squareClicked(MouseHandler context, Square square);
    void pieceClicked(MouseHandler context, Piece piece);
}
