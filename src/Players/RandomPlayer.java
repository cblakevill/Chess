package Players;

import Model.Color;
import Control.GameController;
import Movement.Move;
import Model.Pieces.Piece;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Chess - Created 12/04/2018.
 */
public class RandomPlayer extends Player
{

    private final RandRunner runner = new RandRunner();
    public RandomPlayer(Color color, GameController gc)
    {
        super(color, gc);
    }

    @Override
    public Move beginTurn(Set<Move> moves)
    {

        Thread t = new Thread(runner);

        t.start();
        try
        {
            t.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return runner.getMove();
    }


    public class RandRunner implements Runnable
    {
        public Piece getPiece()
        {
            return piece;
        }

        public Move getMove()
        {
            return move;
        }

        private Piece piece;
        private Move move;
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            piece = null;
            int r;
            int i;
            Set<Move> moves = getGC().getAllMoves(getColor());
            move = null;
            r = new Random().nextInt(moves.size());
            i = 0;
            for(Move m : moves)
            {
                if(i == r)
                {
                    move = m;
                }
                i++;
            }

        }
    }
}
