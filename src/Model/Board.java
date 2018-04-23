package Model;

import Model.Pieces.*;
import Movement.Move;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Chess - Created 19/03/2018.
 */
public class Board
{
    private Square[][] squares;
    private Set<Piece> whitePieces;
    private Set<Piece> blackPieces;
    private LinkedList<Move> moveList;

    public Board()
    {
        this.squares = new Square[8][8];
        this.whitePieces = new HashSet<>();
        this.blackPieces = new HashSet<>();
        this.moveList = new LinkedList<>();
        init();
    }

    private Board(Square[][] squares, LinkedList<Move> moveList)
    {
        this.whitePieces = new HashSet<>();
        this.blackPieces = new HashSet<>();
        this.squares = new Square[8][8];
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                this.squares[y][x] =  squares[y][x].copy();
                if(this.squares[y][x].getPiece() != null)
                {
                    if(this.squares[y][x].getPiece().getColor() == Color.WHITE)
                        this.whitePieces.add(this.squares[y][x].getPiece());
                    else
                        this.blackPieces.add(this.squares[y][x].getPiece());

                }
            }
        }
        this.moveList = new LinkedList<>();
        moveList.forEach(move -> this.moveList.addLast(move.copy()));
    }

    public Square getSquare(Position p)
    {
        return squares[8 - p.rank()][p.file() - 1];
    }

    public Square getSquare(int rank, int file)
    {
        return squares[8 - rank][file - 1];
    }

    public Square[][] getSquares()
    {
        return squares;
    }

    public Board copy()
    {
        return new Board(squares, moveList);
    }

    public void move(Move move)
    {
        Piece piece = move.getPiece();
        getSquare(move.getFrom()).setPiece(null);
        getSquare(move.getTo()).setPiece(piece);
        piece.setPosition(move.getTo());
        piece.incrementMoveCount();
        if(move.isCapture())
        {
            getPieces(move.getCapture().getColor()).remove(move.getCapture());
        }
        moveList.add(move);
    }

    public void undoMove()
    {
        Move move = moveList.removeLast();
        Piece piece = move.getPiece();
        getSquare(move.getTo()).setPiece(null);
        getSquare(move.getFrom()).setPiece(piece);
        piece.setPosition(move.getFrom());
        piece.decrementMoveCount();
        if(move.isCapture())
        {
            getPieces(move.getCapture().getColor()).add(move.getCapture());
            getSquare(move.getTo()).setPiece(move.getCapture());
        }
    }

    private Set<Piece> getPieces(Color color)
    {
        if(color == Color.WHITE)
            return whitePieces;
        else
            return blackPieces;
    }

    private void init()
    {
        for(int y = 1; y <= 8; y++)
        {
            for(int x = 1; x <= 8; x++)
            {
                if(y % 2 == 0 && x % 2 == 0 || y % 2 != 0 && x % 2 != 0)
                    squares[8 - y][x - 1] = new Square(Color.BLACK, new Position(x, y));
                else
                    squares[8 - y][x - 1] = new Square(Color.WHITE, new Position(x, y));
            }
        }

        initBackrow(1, Color.WHITE, whitePieces);
        initPawns(2, Color.WHITE, whitePieces);
        initPawns(7, Color.BLACK, blackPieces);
        initBackrow(8, Color.BLACK, blackPieces);
    }

    private void initPawns(int row, Color color, Set<Piece> pieceSet)
    {
        for(int i = 1; i <= 8; i++)
        {
            Pawn pawn = new Pawn(new Position(i, row), color);
            pieceSet.add(pawn);
            squares[8 - row][i - 1].setPiece(pawn);
        }
    }

    private void initBackrow(int row, Color color, Set<Piece> pieceSet)
    {
        Rook r1 = new Rook(new Position(1, row), color);
        Knight n1 = new Knight(new Position(2, row), color);
        Bishop b1 = new Bishop(new Position(3, row), color);
        Queen q = new Queen(new Position(4, row), color);
        King k = new King(new Position(5, row), color);
        Bishop b2 = new Bishop(new Position(6, row), color);
        Knight n2 = new Knight(new Position(7, row), color);
        Rook r2 = new Rook(new Position(8, row), color);

        Piece[] pieces = {r1, n1, b1, q, k, b2, n2, r2};

        for(Piece piece : pieces)
        {
            pieceSet.add(piece);
            getSquare(piece.getPosition()).setPiece(piece);
        }
    }

    public Set<Piece> getWhitePieces()
    {
        return whitePieces;
    }

    public Set<Piece> getBlackPieces()
    {
        return blackPieces;
    }
}
