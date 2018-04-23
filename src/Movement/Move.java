package Movement;

import Control.GameController;
import Model.Constants;
import Model.Pieces.Pawn;
import Model.Pieces.Piece;
import Model.Position;

import java.util.Objects;

/**
 * Chess - Created 20/03/2018.
 */
public class Move
{
    private Piece capture;
    private Position to;
    private Position from;
    private Piece piece;

    public Move(Piece piece, Position from, Position to, Piece capture)
    {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.capture = capture;
    }

    public Move(Piece piece, Position from, Position to)
    {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.capture = null;
    }

    public Move copy()
    {
        if(isCapture())
            return new Move(piece.copy(), from.copy(), to.copy(), capture.copy());
        return new Move(piece.copy(),  from.copy(), to.copy());
    }

    public Piece getPiece()
    {
        return piece;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    public void execute(Piece piece, GameController gc)
    {
        if(!isValid())
            throw new RuntimeException();
        gc.movePiece(this);
    }

    public boolean isCapture()
    {
        return capture != null;
    }

    public Piece getCapture()
    {
        return capture;
    }

    public Position getTo()
    {
        return to;
    }

    public Position getFrom()
    {
        return from;
    }

    public boolean isValid()
    {
        return to != null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Move)
        {
            Move move = (Move)obj;
            return hashCode() == move.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(from, to, piece.hashCode());
    }

    private boolean isCheck;

    public void setCheck(boolean check)
    {
        isCheck = check;
    }

    public boolean isCheck()
    {
        return isCheck;

    }

    public String algebraicNotation()
    {
        Piece piece = getPiece();
        /**
         if(move.isQueenCastle())
         {
         return "0-0-0";
         }

         if(move.isKingCastle())
         {
         return "0-0";
         }
         **/

        String mv = "";
        if(piece instanceof Pawn)
        {
            if(isCapture())
            {
                mv += getFrom().fileStr();
            }
            else
            {
                mv += getFrom().toString();
            }
        }
        else
        {
            mv += piece.getID();
        }

        /**
         if(move.fileAmbiguous())
         {

         }

         if(move.rankAmbiguous())
         {

         }
         **/

        if(isCapture())
        {
            mv += "x";
        }

        if(!(piece instanceof Pawn) || isCapture())
            mv += getTo();


        if(isCheck())
        {
            mv += "#";
        }

        /**
         if(move.isPromotion())
         {

         }
         **/

        return mv;
    }

    @Override
    public String toString()
    {
        return algebraicNotation();
    }
}
