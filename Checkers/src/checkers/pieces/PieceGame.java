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

        return mat;
    }
}
