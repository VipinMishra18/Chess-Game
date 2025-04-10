package chessgame;

import chessgame.pieces.Bishop;
import chessgame.pieces.King;
import chessgame.pieces.Knight;
import chessgame.pieces.Pawn;
import chessgame.pieces.Queen;
import chessgame.pieces.Rook;

public class Game {
    private Piece[][] board;
    private boolean whiteTurn;
    
    public Game() {
        whiteTurn = true;
        initializeBoard();
    }
    
    private void initializeBoard() {
        board = new Piece[8][8];
        
        // Initialize pawns
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(false);
            board[6][col] = new Pawn(true);
        }
        
        // Initialize rooks
        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);
        
        // Initialize knights
        board[0][1] = new Knight(false);
        board[0][6] = new Knight(false);
        board[7][1] = new Knight(true);
        board[7][6] = new Knight(true);
        
        // Initialize bishops
        board[0][2] = new Bishop(false);
        board[0][5] = new Bishop(false);
        board[7][2] = new Bishop(true);
        board[7][5] = new Bishop(true);
        
        // Initialize queens
        board[0][3] = new Queen(false);
        board[7][3] = new Queen(true);
        
        // Initialize kings
        board[0][4] = new King(false);
        board[7][4] = new King(true);
    }
    
    public Piece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return null;
        return board[row][col];
    }
    
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }
    
    public boolean getCurrentPlayer() {
        return whiteTurn;
    }
    
    public void switchPlayer() {
        whiteTurn = !whiteTurn;
    }
    
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) return false;
        
        Piece piece = board[startRow][startCol];
        if (piece == null) return false;
        
        // Simulate move to check for validity
        Piece[][] tempBoard = copyBoard();
        Piece tempPiece = tempBoard[startRow][startCol];
        tempBoard[endRow][endCol] = tempPiece;
        tempBoard[startRow][startCol] = null;
        
        boolean valid = piece.isValidMove(startRow, startCol, endRow, endCol, board);
        if (!valid) return false;
        
        // Check if move would leave king in check
        if (wouldLeaveKingInCheck(startRow, startCol, endRow, endCol)) {
            return false;
        }
        
        return true;
    }
    
    private boolean wouldLeaveKingInCheck(int startRow, int startCol, int endRow, int endCol) {
        Piece[][] originalBoard = board;
        try {
            // Simulate the move
            board = copyBoard();
            board[endRow][endCol] = board[startRow][startCol];
            board[startRow][startCol] = null;
            
            return isCheck();
        } finally {
            board = originalBoard;
        }
    }
    
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        board[endRow][endCol] = board[startRow][startCol];
        board[startRow][startCol] = null;
    }
    
    public boolean shouldPromotePawn(int row, int col) {
        Piece piece = board[row][col];
        return piece instanceof Pawn && (row == 0 || row == 7);
    }
    
    public boolean isCheck() {
        int kingRow = -1, kingCol = -1;
        boolean currentPlayer = whiteTurn;
        
        // Find the king's position
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece instanceof King && piece.getColor() == currentPlayer) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
            if (kingRow != -1) break;
        }
        
        // Check if any opponent piece can attack the king
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null && piece.getColor() != currentPlayer) {
                    if (piece.isValidMove(row, col, kingRow, kingCol, board)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private Piece[][] copyBoard() {
        Piece[][] copy = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 8);
        }
        return copy;
    }
}