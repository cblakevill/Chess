package Display;

import Control.GameController;
import Model.Pieces.Piece;

/**
 * Chess - Created 28/03/2018.
 */
public class DisplayController
{
    private GameController gc;
    private BoardDisplay bd;

    public DisplayController(GameController gc, BoardDisplay bd)
    {
        this.gc = gc;
        this.bd = bd;
    }

    public void highlightPiece(Piece piece)
    {
        bd.getSquare(piece.getPosition()).highlightSquare();
    }

    public void unhighlightPiece(Piece piece)
    {
        bd.getSquare(piece.getPosition()).unhighlightSquare();
    }
}
