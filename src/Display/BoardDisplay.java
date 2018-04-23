package Display;

import Control.Mouse.MouseHandler;
import Model.Board;
import Control.GameController;
import Control.PieceMovedObserver;
import Model.Pieces.Piece;
import Model.Position;
import Movement.Move;

import javax.swing.*;
import java.awt.*;

/**
 * Chess - Created 19/03/2018.
 */
public class BoardDisplay extends JPanel implements PieceMovedObserver
{
    private SquareDisplay[][] squares;
    private MouseHandler mouseHandler;

    private SquareDisplay currFrom;
    private SquareDisplay currTo;
    private SquareDisplay prevFrom;
    private SquareDisplay prevTo;

    public BoardDisplay(GameController gc, Board board)
    {
        this.mouseHandler = new MouseHandler(gc, new DisplayController(gc, this));
        gc.addToMovePieceNotifier(this);

        setLayout(new GridLayout(8, 8));
        squares = new SquareDisplay[8][8];
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j ++)
            {
                squares[i][j] = new SquareDisplay(board.getSquares()[i][j], mouseHandler);
                if((board.getSquares()[i][j].getPiece()) != null)
                {
                    squares[i][j].setPiece(new PieceDisplay(board.getSquares()[i][j].getPiece(), mouseHandler));
                }
                add(squares[i][j]);
            }
        }

        prevFrom = null;
        prevTo = null;
        currFrom = null;
        currTo = null;
    }

    public SquareDisplay getSquare(Position p)
    {
        return squares[8 - p.rank()][p.file() - 1];
    }

    @Override
    public void notifyPieceMoved(Move move)
    {
        prevFrom = currFrom;
        prevTo = currTo;

        currFrom = getSquare(move.getFrom());
        currTo = getSquare(move.getTo());


        if(prevFrom != null && prevTo != null)
        {
            prevFrom.unhighlightSquare();
            prevTo.unhighlightSquare();
        }

        currFrom.removePiece();
        currTo.setPiece(new PieceDisplay(move.getPiece(), mouseHandler));

        currFrom.highlightSquare();
        currTo.highlightSquare();


    }

    public MouseHandler getMouseHandler()
    {
        return mouseHandler;
    }
}
