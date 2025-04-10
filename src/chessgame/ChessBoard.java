package chessgame;

import javax.swing.*;

import chessgame.pieces.Bishop;
import chessgame.pieces.Knight;
import chessgame.pieces.Queen;
import chessgame.pieces.Rook;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard extends JPanel {
    private static final int BOARD_SIZE = 8;
    private static final int TILE_SIZE = 80;
    private final Game game;
    private final JLabel statusLabel;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private List<Point> possibleMoves = new ArrayList<>();

    public ChessBoard(Game game, JLabel statusLabel) {
        this.game = game;
        this.statusLabel = statusLabel;
        setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / TILE_SIZE;
                int row = e.getY() / TILE_SIZE;
                
                if (col < 0 || col >= BOARD_SIZE || row < 0 || row >= BOARD_SIZE) return;
                
                if (selectedRow == -1 || selectedCol == -1) {
                    // First click - select piece
                    Piece piece = game.getPiece(row, col);
                    if (piece != null && piece.getColor() == game.getCurrentPlayer()) {
                        selectedRow = row;
                        selectedCol = col;
                        updatePossibleMoves();
                        updateStatus("Selected: " + piece);
                    }
                } else {
                    // Second click - move piece
                    if (isPossibleMove(row, col)) {
                        game.movePiece(selectedRow, selectedCol, row, col);
                        
                        if (game.shouldPromotePawn(row, col)) {
                            promotePawn(row, col);
                        }
                        
                        game.switchPlayer();
                        updateStatus(game.getCurrentPlayer() ? "White's turn" : "Black's turn");
                        
                        if (game.isCheck()) {
                            updateStatus((game.getCurrentPlayer() ? "Black" : "White") + " is in check!");
                        }
                    }
                    selectedRow = -1;
                    selectedCol = -1;
                    possibleMoves.clear();
                }
                repaint();
            }
        });
    }

    private void updatePossibleMoves() {
        possibleMoves.clear();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (game.isValidMove(selectedRow, selectedCol, row, col)) {
                    possibleMoves.add(new Point(col, row));
                }
            }
        }
    }

    private boolean isPossibleMove(int row, int col) {
        return possibleMoves.contains(new Point(col, row));
    }

    private void updateStatus(String message) {
        statusLabel.setText(message);
    }

    private void promotePawn(int row, int col) {
        String[] options = {"Queen", "Rook", "Bishop", "Knight"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "Promote pawn to:",
            "Pawn Promotion",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        boolean isWhite = game.getPiece(row, col).getColor();
        switch (choice) {
            case 0: game.setPiece(row, col, new Queen(isWhite)); break;
            case 1: game.setPiece(row, col, new Rook(isWhite)); break;
            case 2: game.setPiece(row, col, new Bishop(isWhite)); break;
            case 3: game.setPiece(row, col, new Knight(isWhite)); break;
            default: game.setPiece(row, col, new Queen(isWhite));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw chess board
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boolean isLight = (row + col) % 2 == 0;
                g.setColor(isLight ? new Color(240, 217, 181) : new Color(181, 136, 99));
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        
        // Highlight possible moves
        g.setColor(new Color(100, 200, 100, 100));
        for (Point move : possibleMoves) {
            g.fillRect(move.x * TILE_SIZE, move.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        
        // Highlight selected piece
        if (selectedRow != -1 && selectedCol != -1) {
            g.setColor(new Color(247, 247, 105, 150));
            g.fillRect(selectedCol * TILE_SIZE, selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        
        // Draw pieces
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = game.getPiece(row, col);
                if (piece != null) {
                    piece.draw(g, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }
}