package Players;

import Control.GameController;
import Control.SimController;
import Model.Color;
import Model.Pieces.Piece;
import Model.Position;
import Model.Square;
import Movement.Move;

import java.util.*;

/**
 * Chess - Created 12/04/2018.
 */
public class GreedyPlayer extends Player
{
    private Color color;

    public GreedyPlayer(Color color, GameController gc)
    {
        super(color, gc);
        this.color = color;
    }

    @Override
    public Move beginTurn(Set<Move> moves)
    {
        try
        {
            Thread.sleep(600);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        int max = -1000000;
        HashMap<Integer, ArrayList<Move>> movemap = new HashMap<>();
        for(Move move_ : moves)
        {
            if(move_ != null)
            {
                SimController con1 = getGC().getSim(color);
                con1.executeMove(move_);
                int cost = con1.getMaterialScore(color);
                if (cost >= max)
                {
                    if(!movemap.containsKey(cost)) movemap.put(cost, new ArrayList<>());
                    movemap.get(cost).add(move_);
                    max = cost;
                }
            }
        }

       return movemap.get(max).get(new Random().nextInt(movemap.get(max).size()));
    }

}
