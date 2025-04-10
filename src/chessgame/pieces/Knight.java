package chessgame.pieces;

import java.awt.Graphics;

import chessgame.Piece;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);
        
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }
        
        return board[endRow][endCol] == null || board[endRow][endCol].getColor() != isWhite;
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♘" : "♞", x, y, tileSize);
    }

}