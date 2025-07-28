package bitcoinGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

// Manages saving/loading the high score
import bitcoinGUI.ScoreManager;

public class GameGUI extends JFrame {
    private static GameGUI gui;
    private static Image backgroundIMG, drop, gameOver, restartGame;
    private static long lastFrameTime;
    private static float dropY = -150, dropX;
    private static float dropSpeed = 22;
    private static int score = 0;
    private static int highScore = 0;
    private static final float restartX = 200, restartY = 350;

    public static void createWindow() throws IOException {
        // Load images
        backgroundIMG = ImageIO.read(GameGUI.class.getResourceAsStream("backgroundIMG.png"));
        gameOver = ImageIO.read(GameGUI.class.getResourceAsStream("gameOver.png"));
        drop = ImageIO.read(GameGUI.class.getResourceAsStream("drop.png"));
        restartGame = ImageIO.read(GameGUI.class.getResourceAsStream("restartGame.png"));

        // Load high score
        highScore = ScoreManager.loadHighScore();

        // Set up JFrame
        gui = new GameGUI();
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setTitle("Bitcoin Catcher | Score: " + score + " | High Score: " + highScore);
        gui.setResizable(false);
        gui.setSize(900, 600);
        gui.setLocationRelativeTo(null);

        lastFrameTime = System.nanoTime();

        // Initialize game field
        GameField gameField = new GameField();
        gameField.addMouseListener(new DropMouseListener());
        gui.add(gameField);
        gui.setVisible(true);
    }

    // Logic for repainting
    public static void onRepaint(Graphics g) {
        g.drawImage(backgroundIMG, 0, 0, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score + "  High Score: " + highScore, 10, 25);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.00000001f;
        lastFrameTime = currentTime;

        dropY += dropSpeed * deltaTime;

        if (dropY > gui.getHeight()) {
            g.drawImage(gameOver, 25, 0, null);
            g.drawImage(restartGame, (int) restartX, (int) restartY, null);
        } else {
            g.drawImage(drop, (int) dropX, (int) dropY, null);
        }
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            if (dropY < getHeight()) repaint();
        }
    }

    private static class DropMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX(), y = e.getY();

            // Check if the drop is clicked
            if (isDropClicked(x, y)) {
                resetDrop();
                score++;
                dropSpeed += 3;
                if (score > highScore) {
                    highScore = score;
                    ScoreManager.saveHighScore(highScore);
                }
                gui.setTitle("Bitcoin Catcher | Score: " + score + " | High Score: " + highScore);
            }

            // Check if restart button is clicked
            if (isRestartClicked(x, y) && dropY > gui.getHeight()) {
                resetGame();
            }
        }

        private boolean isDropClicked(int x, int y) {
            return x >= dropX && x <= dropX + drop.getWidth(null) &&
                   y >= dropY && y <= dropY + drop.getHeight(null);
        }

        private boolean isRestartClicked(int x, int y) {
            return x >= restartX && x <= restartX + restartGame.getWidth(null) &&
                   y >= restartY && y <= restartY + restartGame.getHeight(null);
        }

        private void resetDrop() {
            dropY = -100 - (float) (Math.random() * 300);
            dropX = (float) (Math.random() * (gui.getWidth() - drop.getWidth(null)));
        }

        private void resetGame() {
            score = 0;
            dropSpeed = 22;
            resetDrop();
            gui.setTitle("Bitcoin Catcher | Score: " + score + " | High Score: " + highScore);
        }
    }
}
