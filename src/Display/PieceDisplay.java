package Display;

import Control.Mouse.MouseHandler;
import Model.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Chess - Created 19/03/2018.
 */
public class PieceDisplay extends JPanel
{
    private Image image;

    public PieceDisplay(Piece piece, MouseHandler mouseHandler)
    {
        this.image = ResourceMap.getImage(piece.getColor(), piece.getName());
        setOpaque(false);
        setPreferredSize(new Dimension(SquareDisplay.SQUARE_SIZE, SquareDisplay.SQUARE_SIZE));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            mouseHandler.pieceClicked(piece);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
