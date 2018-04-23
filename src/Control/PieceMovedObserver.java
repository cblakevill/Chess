package Control;

import Model.Pieces.Piece;
import Model.Position;
import Movement.Move;

/**
 * Chess - Created 25/03/2018.
 */
public interface PieceMovedObserver
{
    void notifyPieceMoved(Move move);
}
