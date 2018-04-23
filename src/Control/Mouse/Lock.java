package Control.Mouse;

/**
 * Chess - Created 12/04/2018.
 */
public class Lock
{
    public void unlock()
    {
        notifyAll();
    }
}
