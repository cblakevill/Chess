import Players.*;
import Control.GameController;
import Movement.MoveFinder;
import Display.BoardDisplay;
import Model.*;
import Model.Color;

import javax.swing.*;
import java.awt.*;

/**
 * Chess - Created 19/03/2018.
 */
public class Main
{
    public static void main(String[] args)
    {

        Board board = new Board();
        MoveFinder mv = new MoveFinder(board);
        GameController gc = new GameController(board, mv);
        BoardDisplay bd = new BoardDisplay(gc, board);
        //player types are mouse, random and greedy
        Player white = new MousePlayer(Color.WHITE, gc, bd.getMouseHandler());
        Player black = new GreedyPlayer(Color.BLACK, gc);
        gc.setPlayer(white, Color.WHITE);
        gc.setPlayer(black, Color.BLACK);

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JFrame();
                frame.setResizable(false);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(bd);
                frame.pack();
            }
        });

        gc.start();
    }
}
