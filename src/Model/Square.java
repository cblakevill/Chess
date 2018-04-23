package Model;

import Model.Pieces.Piece;

import java.util.Objects;

/**
 * Chess - Created 19/03/2018.
 */
public class Square
{
    private Piece piece;
    private Color color;
    private Position position;

    public Square(Color color, Position position)
    {
        this.color = color;
        this.position = position;
    }

    private Square(Piece piece, Color color, Position position)
    {
        if(piece != null)
            this.piece = piece;
        this.color = color;
        this.position = position;
    }

    public Square copy()
    {
        if(piece != null)
            return new Square(piece.copy(), color, position.copy());
        return new Square(null, color, position.copy());
    }

    public Piece getPiece()
    {
        return piece;
    }

    public Color getColor()
    {
        return color;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    public Position getPosition()
    {
        return position;
    }

    public boolean isEmpty()
    {
        return piece == null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Square)
        {
            Square square = (Square)obj;
            if(this.piece == null || square.piece == null)
            {
                return this.piece == square.piece && this.position.equals(square.position);
            }
            return this.piece.equals(square.piece) && this.position.equals(square.position);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(piece, position);
    }
}
