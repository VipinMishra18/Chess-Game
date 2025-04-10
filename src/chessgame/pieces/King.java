package chessgame.pieces;

import java.awt.Graphics;

import chessgame.Piece;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);
        
        if (rowDiff > 1 || colDiff > 1) return false;
        
        return board[endRow][endCol] == null || board[endRow][endCol].getColor() != isWhite;
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♔" : "♚", x, y, tileSize);
    }

}