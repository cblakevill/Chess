package Control;

import Players.Player;
import Model.Color;
import Movement.Move;

import java.util.Set;

/**
 * Chess - Created 14/04/2018.
 */
public class SimPlayer extends Player
{
    public SimPlayer(Color color, GameController gc)
    {
        super(color, gc);
    }

    @Override
    public Move beginTurn(Set<Move> moves)
    {
        return null;
    }
}
