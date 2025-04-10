package chessgame.pieces;

import java.awt.Graphics;
import chessgame.Piece;

public class Pawn extends Piece {
    private boolean hasMoved = false;
    
    public Pawn(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        int direction = isWhite ? -1 : 1;
        
        // Standard move
        if (startCol == endCol && board[endRow][endCol] == null) {
            // Always allow single step forward
            if (startRow + direction == endRow) {
                return true;
            }
            // Only allow double step from starting position (2nd or 7th rank)
            if (!hasMoved && 
                ((isWhite && startRow == 6) || (!isWhite && startRow == 1)) &&
                startRow + 2 * direction == endRow && 
                board[startRow + direction][startCol] == null) {
                return true;
            }
        }
        
        // Capture moves remain unchanged
        if (Math.abs(startCol - endCol) == 1 && startRow + direction == endRow && 
            board[endRow][endCol] != null && board[endRow][endCol].getColor() != isWhite) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public void move(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        // Mark the pawn as moved after any move
        hasMoved = true;
        super.move(startRow, startCol, endRow, endCol, board);
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♙" : "♟", x, y, tileSize);
    }
}