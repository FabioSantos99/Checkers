package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class PieceGame extends CheckersPiece {

    public PieceGame(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position(0, 0);

    if (getColor() == Color.WHITE) {
        // Movimento diagonal simples para cima-esquerda
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // Movimento diagonal simples para cima-direita
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // Captura diagonal para cima-esquerda
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            Position p2 = new Position(position.getRow() - 2, position.getColumn() - 2);
            if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
                mat[p2.getRow()][p2.getColumn()] = true;
            }
        }

        // Captura diagonal para cima-direita
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            Position p2 = new Position(position.getRow() - 2, position.getColumn() + 2);
            if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
                mat[p2.getRow()][p2.getColumn()] = true;
            }
        }
    } else {
        // Movimento diagonal simples para baixo-esquerda
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // Movimento diagonal simples para baixo-direita
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // Captura diagonal para baixo-esquerda
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            Position p2 = new Position(position.getRow() + 2, position.getColumn() - 2);
            if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
                mat[p2.getRow()][p2.getColumn()] = true;
            }
        }

        // Captura diagonal para baixo-direita
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            Position p2 = new Position(position.getRow() + 2, position.getColumn() + 2);
            if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
                mat[p2.getRow()][p2.getColumn()] = true;
            }
        }
    }
    return mat;
}

}
