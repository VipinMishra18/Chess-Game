package chessgame;

import java.awt.*;

public abstract class Piece {
    protected boolean isWhite;
    
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }
    
    // Getter for color
    public boolean getColor() {
        return isWhite;
    }
    
    // Common drawing utility method
    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Piece[][] board);
    
    public abstract void draw(Graphics g, int x, int y, int tileSize);
    
    protected void drawPiece(Graphics g, String symbol, int x, int y, int tileSize) {
        g.setFont(new Font("SansSerif", Font.PLAIN, tileSize - 20));
        g.setColor(isWhite ? Color.WHITE : Color.BLACK);
        
        FontMetrics metrics = g.getFontMetrics();
        int textX = x + (tileSize - metrics.stringWidth(symbol)) / 2;
        int textY = y + ((tileSize - metrics.getHeight()) / 2) + metrics.getAscent();
        
        g.drawString(symbol, textX, textY);
    }
    
    // Optional: Basic move method that can be overridden by pieces
    public void move(int startRow, int startCol, int endRow, int endCol, Piece[][] board) {
        // Default implementation just moves the piece
        board[endRow][endCol] = this;
        board[startRow][startCol] = null;
    }
    
    // Improved toString with color information
    @Override
    public String toString() {
        return (isWhite ? "White " : "Black ") + getClass().getSimpleName();
    }
    
    // Helper method to check if coordinates are within bounds
    protected boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

}