package Control;

import Pieces.Piece;

import java.util.ArrayList;

/**
 * Chess - Created 17/03/2018.
 */
public class Board
{
    private Tile[][] tiles;
    private Team white;
    private Team black;

    public Board()
    {
        white = new Team("white");
        black = new Team("black");
        tiles = new Tile[8][8];
        initialisPieces();
    }

    private void initialisPieces()
    {
        for(int i = 0; i < 8; i++)
            tiles[6][i].placePiece(white.getPawn()[i]);
        for(int i = 0; i < 8; i++)
            tiles[1][i].placePiece(black.getPawn()[i]);

        placeBackPieces(white, 7);
        placeBackPieces(black, 0);
    }

    private void placeBackPieces(Team team, int row)
    {
        for(int i = 0; i < 8; i++)
        {
            switch(i)
            {
                case 0:
                    tiles[row][i].placePiece(team.getRook()[0]);
                    break;
                case 1:
                    tiles[row][i].placePiece(team.getKnight()[0]);
                    break;
                case 2:
                    tiles[row][i].placePiece(team.getBishop()[0]);
                    break;
                case 3:
                    tiles[row][i].placePiece(team.getQueen());
                    break;
                case 4:
                    tiles[row][i].placePiece(team.getKing());
                    break;
                case 5:
                    tiles[row][i].placePiece(team.getBishop()[1]);
                    break;
                case 6:
                    tiles[row][i].placePiece(team.getKnight()[1]);
                    break;
                case 7:
                    tiles[row][i].placePiece(team.getRook()[1]);
                    break;
            }
        }
    }

    public class Tile
    {
        public Tile()
        {

        }

        public void placePiece(Piece piece)
        {

        }

        public void removePiece()
        {

        }

        public void highLightPiece()
        {

        }
    }
}
