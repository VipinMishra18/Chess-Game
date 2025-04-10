package chessgame.pieces;

import java.awt.Graphics;

import chessgame.Piece;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) return false;
        
        int rowStep = startRow < endRow ? 1 : -1;
        int colStep = startCol < endCol ? 1 : -1;
        
        int row = startRow + rowStep;
        int col = startCol + colStep;
        
        while (row != endRow && col != endCol) {
            if (board[row][col] != null) return false;
            row += rowStep;
            col += colStep;
        }
        
        return board[endRow][endCol] == null || board[endRow][endCol].getColor() != isWhite;
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♗" : "♝", x, y, tileSize);
    }

}