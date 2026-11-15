package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class CheckersQueen extends CheckersPiece {

    public CheckersQueen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // As 4 diagonais: superior esquerda, superior direita, inferior esquerda, inferior direita
        int[][] directions = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };

        for (int[] d : directions) {
            Position p = new Position(position.getRow() + d[0], position.getColumn() + d[1]);

            // Movimento livre pela diagonal
            while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
                p.setValues(p.getRow() + d[0], p.getColumn() + d[1]);
            }

            // Captura: peca adversaria seguida de casa(s) vazia(s)
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                p.setValues(p.getRow() + d[0], p.getColumn() + d[1]);
                while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                    mat[p.getRow()][p.getColumn()] = true;
                    p.setValues(p.getRow() + d[0], p.getColumn() + d[1]);
                }
            }
        }

        return mat;
    }
}
