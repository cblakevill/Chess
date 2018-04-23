package Display;

import Control.Mouse.MouseHandler;
import Model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Chess - Created 19/03/2018.
 */
public class SquareDisplay extends JPanel
{
    public static final int COLOR_WHITE = 0xF3EBD7;
    public static final int COLOR_BLACK = 0xA37754;
    public static final int COLOR_HIGHLIGHT_WHITE = 0xEB9D9C;
    public static final int COLOR_HIGHLIGHT_BLACK = 0xEB9090;
    public static final int SQUARE_SIZE = 100;

    private Square square;
    private Color colorSquare;
    private Color colorSquareHighlight;

    public SquareDisplay(Square square, MouseHandler mouseHandler)
    {
        this.square = square;

        boolean white = (square.getColor() == Model.Color.WHITE);
        colorSquare = new Color(white ? COLOR_WHITE : COLOR_BLACK);
        colorSquareHighlight = new Color(white ? COLOR_HIGHLIGHT_WHITE : COLOR_HIGHLIGHT_BLACK);

        this.setBackground(colorSquare);
        this.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
        this.setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                mouseHandler.squareClicked(square);
            }
        });
    }

    public void setPiece(PieceDisplay piece)
    {
        removePiece();
        this.add(piece, BorderLayout.CENTER);
    }

    public void removePiece()
    {
        removeAll();
        unhighlightSquare();
        repaint();
    }

    public void highlightSquare()
    {
        setBackground(colorSquareHighlight);
        revalidate();
    }

    public void unhighlightSquare()
    {
        setBorder(null);
        setBackground(colorSquare);
    }
}
