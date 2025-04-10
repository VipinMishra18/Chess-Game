package chessgame.pieces;

import java.awt.Graphics;
<<<<<<< HEAD
=======

>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
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
<<<<<<< HEAD
            // Always allow single step forward
            if (startRow + direction == endRow) {
                return true;
            }
            // Only allow double step from starting position (2nd or 7th rank)
            if (!hasMoved && 
                ((isWhite && startRow == 6) || (!isWhite && startRow == 1)) &&
                startRow + 2 * direction == endRow && 
=======
            if (startRow + direction == endRow) return true;
            if (!hasMoved && startRow + 2 * direction == endRow && 
>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
                board[startRow + direction][startCol] == null) {
                return true;
            }
        }
        
<<<<<<< HEAD
        // Capture moves remain unchanged
=======
        // Capture
>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
        if (Math.abs(startCol - endCol) == 1 && startRow + direction == endRow && 
            board[endRow][endCol] != null && board[endRow][endCol].getColor() != isWhite) {
            return true;
        }
        
        return false;
    }
    
    @Override
<<<<<<< HEAD
    public void move(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        // Mark the pawn as moved after any move
        hasMoved = true;
        super.move(startRow, startCol, endRow, endCol, board);
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♙" : "♟", x, y, tileSize);
    }
=======
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♙" : "♟", x, y, tileSize);
    }

>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
}