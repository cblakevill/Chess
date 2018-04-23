package Players;

import Control.SimController;
import Model.Color;
import Control.GameController;
import Model.Pieces.Piece;
import Movement.Move;
import Control.TurnStartObserver;

import java.util.HashSet;
import java.util.Set;

/**
 * Chess - Created 12/04/2018.
 */
public abstract class Player implements TurnStartObserver
{
    private Color color;
    private GameController gc;

    public Player(Color color, GameController gc)
    {
        gc.setPlayer(this, color);
        this.color = color;
        this.gc = gc;
    }

    public Color getColor()
    {
        return color;
    }

    public GameController getGC()
    {
        return gc;
    }

    @Override
    public abstract Move beginTurn(Set<Move> moves);

    public Move doTurn()
    {
        Set<Move> moves = new HashSet<>();
        SimController sim = gc.getSim(color);
        for(Move m : sim.getAllMoves(color))
        {
            if(!sim.leadsToCheck(m))
                moves.add(m);
        }
        Move move =  beginTurn(moves);
        Piece capture = move.getCapture();
        if(capture != null)
            capture = getGC().getPiece(capture.getPosition());
        return new Move(getGC().getPiece(move.getFrom()),move.getFrom(),move.getTo(), capture);
    }
}
