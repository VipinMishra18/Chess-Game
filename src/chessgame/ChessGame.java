package chessgame;

import java.awt.*;

import javax.swing.*;

public class ChessGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            
            Game game = new Game();
            JLabel statusLabel = new JLabel("White's turn", SwingConstants.CENTER);
            statusLabel.setBorder(BorderFactory.createEtchedBorder());
            statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(new ChessBoard(game, statusLabel), BorderLayout.CENTER);
            mainPanel.add(statusLabel, BorderLayout.SOUTH);
            
            frame.add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}