package Players;

import Control.Mouse.MouseHandler;
import Model.Color;
import Control.GameController;
import Control.Mouse.Lock;
import Movement.Move;

import java.util.Set;

/**
 * Chess - Created 12/04/2018.
 */
public class MousePlayer extends Player
{
    private MouseHandler mh;
    private final Lock lock = new Lock();

    public MousePlayer(Color color, GameController gc, MouseHandler mh)
    {
        super(color, gc);
        this.mh = mh;
        if (color == Color.WHITE)
            mh.setCurrentTurn(true);
    }

    @Override
    public Move beginTurn(Set<Move> moves)
    {
        mh.setCurrentTurn(true);
        mh.setLock(lock);

        synchronized (lock)
        {
            try
            {
                lock.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return mh.getMove();
    }

}
