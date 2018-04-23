package Control;

import Players.Player;
import Model.*;
import Movement.Move;
import Movement.MoveFinder;
import Model.Pieces.Pawn;
import Model.Pieces.Piece;

import java.util.*;

/**
 * Chess - Created 27/03/2018.
 */
public class GameController
{
    protected Player currentPlayer;
    private Board board;
    private MoveFinder moveFinder;
    protected int turnCount;
    private List<PieceMovedObserver> pmObsList;
    protected Player whitePlayer;
    protected Player blackPlayer;
    protected Set<Move> whiteInCheck;
    protected Set<Move> blackInCheck;

    public GameController(Board board, MoveFinder moveFinder)
    {
        this.board = board;
        this.moveFinder = moveFinder;
        this.turnCount = 1;
        this.pmObsList = new LinkedList<>();
        this.moveFinder.setGc(this);
    }

    protected GameController(Board board, MoveFinder moveFinder, int turnCount)
    {
        this.board = board;
        this.moveFinder = moveFinder;
        this.turnCount = turnCount;
    }

    public void start()
    {
        if(whitePlayer != null && blackPlayer != null)
        {
            this.currentPlayer = whitePlayer;
            while (true)
            {
                while (!executeMove(currentPlayer.doTurn()));
                currentPlayer = (currentPlayer.getColor() == Color.WHITE) ? blackPlayer : whitePlayer;
            }
        }
    }

    public void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayerAgent()
    {
        return currentPlayer;
    }

    public Color getCurrentPlayer()
    {
        return currentPlayer.getColor();
    }

    public boolean executeMove(Move move)
    {
        Piece piece = move.getPiece();
        if(getSquare(piece.getPosition()).getPiece() != null &&
                getMoves(piece).stream().anyMatch(mv -> mv.algebraicNotation().equals(move.algebraicNotation())))
        {
            if(leadsToCheck(move))
                return false;

                for (PieceMovedObserver observer : pmObsList)
                    observer.notifyPieceMoved(move);

                move.execute(piece, this);
                turnCount++;
                calcIfCheck();
                move.setCheck(isCheck());
                System.out.println((turnCount - 1) + ". == " + move.algebraicNotation() + " ==");
                return true;

        }
        System.out.println("not a valid move (" + move.algebraicNotation() + ")");
        return false;
    }

    public boolean leadsToCheck(Move move)
    {
        SimController sim = getSim(currentPlayer.getColor());
        sim.executeMove(move);
        for(Move m : sim.getAllMoves(sim.getCurrentPlayer()))
        {
            if (m.isCapture() && m.getCapture().getID().equals(Constants.ID_KING))
            {
                sim.undoMove();
                return true;
            }
        }
        return false;
    }

    public Square getSquare(Position position)
    {
        return board.getSquare(position);
    }

    public Set<Move> getAllMoves(Color color)
    {
        Set<Move> moves = new HashSet<>();
        for(Piece p : getPieces(color))
        {
            moves.addAll(getMoves(p));
        }
        return moves;
    }

    public void setTurnCount(int turnCount)
    {
        this.turnCount = turnCount;
    }

    public void movePiece(Move move)
    {
        board.move(move);
    }

    public Piece getPiece(Position position)
    {
        return board.getSquare(position).getPiece();
    }

    public int getTurnCount()
    {
        return turnCount;
    }

    public Set<Move> getMoves(Piece piece)
    {
        Piece k = piece;
        for(Piece p : getPieces(piece.getColor()))
        {
            if(p.getID().equals(Constants.ID_KING))
                k=p;
        }
        return piece.getMoves(moveFinder, turnCount,k.getPosition());
    }

    public void addToMovePieceNotifier(PieceMovedObserver observer)
    {
        pmObsList.add(observer);
    }

    public Set<Piece> getPieces(Color color)
    {
        if(color == Color.WHITE)
            return board.getWhitePieces();
        else
            return board.getBlackPieces();
    }

    public void undoMove()
    {
        turnCount--;
        board.undoMove();
    }

    public SimController getSim(Color color)
    {
        Board boardCopy = board.copy();
        return new SimController(boardCopy, new MoveFinder(boardCopy), color, turnCount);
    }

    public Set<Move> checkMoves;
    public void calcIfCheck()
    {
        blackInCheck = new HashSet<>();
        whiteInCheck = new HashSet<>();
        checkMoves = (currentPlayer.getColor() == Color.WHITE ? blackInCheck : whiteInCheck);
        for(Move move : getAllMoves(currentPlayer.getColor()))
        {
            if (move.isCapture() && move.getCapture().getID().equals(Constants.ID_KING))
            {
                checkMoves.add(move);
            }
        }

        if(!checkMoves.isEmpty())
        {
            for (Piece p : new HashSet<>(getPieces(currentPlayer.getColor().opposite())))
            {
                p.setMoves(moveFinder.checkMoves(getMoves(p), currentPlayer.getColor().opposite()));
            }
        }
    }

    public boolean isCheck()
    {
        return checkMoves.size() != 0;
    }

    public void setPlayer(Player player, Color color)
    {
        if(color == Color.WHITE)
            whitePlayer = player;
        else
            blackPlayer = player;
    }
}
