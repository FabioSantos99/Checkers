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

        Position p = new Position(0,0);

         // Movimentos diagonais (superior esquerda)
         p.setValues(position.getRow() - 1, position.getColumn() - 1);
         while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
             p.setValues(p.getRow() - 1, p.getColumn() - 1);
         }
         if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
         }
 
         // Movimentos diagonais (superior direita)
         p.setValues(position.getRow() - 1, position.getColumn() + 1);
         while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
             p.setValues(p.getRow() - 1, p.getColumn() + 1);
         }
         if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
         }
 
         // Movimentos diagonais (inferior esquerda)
         p.setValues(position.getRow() + 1, position.getColumn() - 1);
         while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
             p.setValues(p.getRow() + 1, p.getColumn() - 1);
         }
         if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
         }
 
         // Movimentos diagonais (inferior direita)
         p.setValues(position.getRow() + 1, position.getColumn() + 1);
         while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
             p.setValues(p.getRow() + 1, p.getColumn() + 1);
         }
         if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
             mat[p.getRow()][p.getColumn()] = true;
         }
 
         return mat;

    }
}
