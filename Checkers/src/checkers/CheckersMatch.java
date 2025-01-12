package checkers;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import checkers.pieces.CheckersQueen;
import checkers.pieces.PieceGame;

public class CheckersMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;

    public CheckersMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public CheckersPiece[][] getPieces() {
        CheckersPiece[][] mat = new CheckersPiece[board.getRows()][board.getColumns()];

        for(int i=0; i<board.getRows(); i++) {
            for (int j=0; j<board.getColumns(); j++) {
                mat[i][j] = (CheckersPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(CheckersPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    
    public CheckersPiece performCheckersMove(CheckersPosition sourcePosition, CheckersPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        CheckersPiece movedPiece = (CheckersPiece) board.piece(target);

        // CheckersQueen Promotion
        if (movedPiece.getColor() == Color.WHITE && target.getRow() == 0 || movedPiece.getColor() == Color.BLACK && target.getRow() == board.getRows() - 1) {
                            board.removePiece(target);
                            board.placePiece(new CheckersQueen(board, movedPiece.getColor()), target);
                
                        }
                
                        return (CheckersPiece)capturedPiece;
                    }
                
                
                    
        
                    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new CheckersException("There is no piece on source position");
        }

        if (currentPlayer != ((CheckersPiece)board.piece(position)).getColor()) {
            throw new CheckersException("The chosen piece is not yours");
        }

        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new CheckersException("There is no possible moves for the chosen piece");
        }

        if (anyCaptureAvailable() && !isCaptureMove(position, position)) {
            throw new CheckersException("You must capture an opponent piece");
        }
    }

    private boolean anyCaptureAvailable() {
        // Check the board for any piece that can capture
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Position pos = new Position(i, j);
                if (board.thereIsAPiece(pos) && board.piece(pos).isThereAnyPossibleMove()) {
                    if (canCapture(pos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canCapture(Position source) {
        boolean[][] moves = board.piece(source).possibleMoves();
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                if (moves[i][j]) {
                    Position target = new Position(i, j);
                    if (isCaptureMove(source, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isCaptureMove(Position source, Position target) {
        int rowDiff = Math.abs(source.getRow() - target.getRow());
        int colDiff = Math.abs(source.getColumn() - target.getColumn());
        if (rowDiff == 2 && colDiff == 2) {
            // Check for an opponent piece between source and target
            int midRow = (source.getRow() + target.getRow()) / 2;
            int midCol = (source.getColumn() + target.getColumn()) / 2;
            Position midPosition = new Position(midRow, midCol);
    
            // Ensure there's an opponent piece in the middle
            if (board.thereIsAPiece(midPosition)) {
                Piece midPiece = board.piece(midPosition);
                return midPiece != null && ((CheckersPiece) midPiece).getColor() != ((CheckersPiece) board.piece(source)).getColor();
            }
        }
        return false;
    }

    private void validateTargetPosition(Position source, Position target) {

        if (!board.piece(source).possibleMove(target)) {
            throw new CheckersException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, CheckersPiece piece) {
        board.placePiece(piece, new CheckersPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new PieceGame(board, Color.WHITE));
        placeNewPiece('c', 1, new PieceGame(board, Color.WHITE));
        placeNewPiece('e', 1, new PieceGame(board, Color.WHITE));
        placeNewPiece('g', 1, new PieceGame(board, Color.WHITE));
        placeNewPiece('b', 2, new PieceGame(board, Color.WHITE));
        placeNewPiece('d', 2, new PieceGame(board, Color.WHITE));
        placeNewPiece('f', 2, new PieceGame(board, Color.WHITE));
        placeNewPiece('h', 2, new PieceGame(board, Color.WHITE));
        placeNewPiece('a', 3, new PieceGame(board, Color.WHITE));
        placeNewPiece('c', 3, new PieceGame(board, Color.WHITE));
        placeNewPiece('e', 3, new PieceGame(board, Color.WHITE));
        placeNewPiece('g', 3, new PieceGame(board, Color.WHITE));

        placeNewPiece('b', 8, new PieceGame(board, Color.BLACK));
        placeNewPiece('d', 8, new PieceGame(board, Color.BLACK));
        placeNewPiece('f', 8, new PieceGame(board, Color.BLACK));
        placeNewPiece('h', 8, new PieceGame(board, Color.BLACK));
        placeNewPiece('a', 7, new PieceGame(board, Color.BLACK));
        placeNewPiece('c', 7, new PieceGame(board, Color.BLACK));
        placeNewPiece('e', 7, new PieceGame(board, Color.BLACK));
        placeNewPiece('g', 7, new PieceGame(board, Color.BLACK));
        placeNewPiece('b', 6, new PieceGame(board, Color.BLACK));
        placeNewPiece('d', 6, new PieceGame(board, Color.BLACK));
        placeNewPiece('f', 6, new PieceGame(board, Color.BLACK));
        placeNewPiece('h', 6, new PieceGame(board, Color.BLACK));
        
        
    }
}
