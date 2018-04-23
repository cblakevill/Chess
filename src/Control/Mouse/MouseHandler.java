package Control.Mouse;

import Control.GameController;
import Display.DisplayController;
import Movement.Move;
import Model.Pieces.Piece;
import Model.Square;

/**
 * Chess - Created 27/03/2018.
 */
public class MouseHandler
{
    private Piece pieceSelected;
    private MouseState state;
    private GameController gc;
    private DisplayController dc;
    private boolean currentTurn;
    private Lock lock;
    private Move move;

    public MouseHandler(GameController gc, DisplayController dc)
    {
        this.gc = gc;
        this.dc = dc;
        this.state = new NoPieceSelected();
        this.pieceSelected = null;
        this.currentTurn = false;
    }

    public void squareClicked(Square square)
    {
        state.squareClicked(this, square);
    }

    public void pieceClicked(Piece piece)
    {
        state.pieceClicked(this, piece);
    }

    public void setState(MouseState state)
    {
        this.state = state;
    }

    public Piece getPieceSelected()
    {
        return pieceSelected;
    }

    public DisplayController getDC()
    {
        return dc;
    }

    public GameController getGC()
    {
        return gc;
    }

    public void setPieceSelected(Piece pieceSelected)
    {
        this.pieceSelected = pieceSelected;
    }

    public void setCurrentTurn(boolean currentTurn)
    {
        this.currentTurn = currentTurn;
    }

    public boolean isCurrentTurn()
    {
        return currentTurn;
    }

    public Lock getLock()
    {
        return lock;
    }

    public void setLock(Lock lock)
    {
        this.lock = lock;
    }

    public Move getMove()
    {
        return move;
    }

    public void setMove(Move move)
    {
        this.move = move;
    }
}
