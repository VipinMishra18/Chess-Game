package chessgame.pieces;

import java.awt.Graphics;

import chessgame.Piece;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }
    
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        if (startRow != endRow && startCol != endCol) return false;
        
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
        
        return board[endRow][endCol] == null || board[endRow][endCol].getColor() != isWhite;
    }
    
    @Override
    public void draw(Graphics g, int x, int y, int tileSize) {
        drawPiece(g, isWhite ? "♖" : "♜", x, y, tileSize);
    }

}