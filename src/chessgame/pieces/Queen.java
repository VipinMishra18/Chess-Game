package chessgame.pieces;

import java.awt.Graphics;

import chessgame.Piece;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        boolean isStraight = startRow == endRow || startCol == endCol;
        boolean isDiagonal = Math.abs(startRow - endRow) == Math.abs(startCol - endCol);
        
        if (!isStraight && !isDiagonal) return false;
        
        if (isStraight) {
            if (startRow == endRow) {
                int colStep = startCol < endCol ? 1 : -1;
                for (int col = startCol + colStep; col != endCol; col += colStep) {
                    if (board[startRow][col] != null) return false;
                }
            } else {
                int rowStep = startRow < endRow ? 1 : -1;
                for (int row = startRow + rowStep; row != endRow; row += rowStep) {
                    if (board[row][startCol] != null) return false;
                }
            }
        } else {
            int rowStep = startRow < endRow ? 1 : -1;
            int colStep = startCol < endCol ? 1 : -1;
            
            int row = startRow + rowStep;
            int col = startCol + colStep;
            
            while (row != endRow && col != endCol) {
                if (board[row][col] != null) return false;
                row += rowStep;
                col += colStep;
            }
        }
        
        return board[endRow][endCol] == null || board[endRow][endCol].getColor() != isWhite;
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♕" : "♛", x, y, tileSize);
    }
	
}