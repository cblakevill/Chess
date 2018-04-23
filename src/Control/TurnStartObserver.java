package Control;

import Movement.Move;

import java.util.Set;

/**
 * Chess - Created 12/04/2018.
 */
public interface TurnStartObserver
{
    Move beginTurn(Set<Move> moves);
}
