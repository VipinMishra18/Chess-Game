package chessgame;

import java.awt.*;

public abstract class Piece {
    protected boolean isWhite;
    
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }
    
<<<<<<< HEAD
    // Getter for color
=======
>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
    public boolean getColor() {
        return isWhite;
    }
    
<<<<<<< HEAD
    // Movement validation - must be implemented by each piece
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board);
    
    // Drawing logic - must be implemented by each piece
    public abstract void draw(Graphics g, int x, int y, int tileSize);
    
    // Common drawing utility method
=======
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board);
    
    public abstract void draw(Graphics g, int x, int y, int tileSize);
    
>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
    protected void drawPiece(Graphics g, String symbol, int x, int y, int tileSize) {
        g.setFont(new Font("SansSerif", Font.PLAIN, tileSize - 20));
        g.setColor(isWhite ? Color.WHITE : Color.BLACK);
        
        FontMetrics metrics = g.getFontMetrics();
        int textX = x + (tileSize - metrics.stringWidth(symbol)) / 2;
        int textY = y + ((tileSize - metrics.getHeight()) / 2) + metrics.getAscent();
        
        g.drawString(symbol, textX, textY);
    }
    
<<<<<<< HEAD
    // Optional: Basic move method that can be overridden by pieces
    public void move(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        // Default implementation just moves the piece
        board[endRow][endCol] = this;
        board[startRow][startCol] = null;
    }
    
    // Improved toString with color information
=======
>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
    @Override
    public String toString() {
        return (isWhite ? "White " : "Black ") + getClass().getSimpleName();
    }
<<<<<<< HEAD
    
    // Helper method to check if coordinates are within bounds
    protected boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
=======
>>>>>>> 7f51f23a094c9fd7d4b145133a0ab0ac27cf2471
}