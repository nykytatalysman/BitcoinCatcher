package bitcoinGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameGUI extends JFrame {
    private static GameGUI gui;
    private static Image backgroundIMG;
    private static Image drop;
    private static Image gameOver;
    private static long lastFrameTime;
    private static float dropY;
    private static float dropX;
    private static float dropSpeed = 220;
        public static void createWindow() throws IOException {
            //Creating and setting up the game window
                backgroundIMG = ImageIO.read(GameGUI.class.getResourceAsStream("backgroundIMG.png"));
                gameOver = ImageIO.read(GameGUI.class.getResourceAsStream("gameOver.png"));
                drop = ImageIO.read(GameGUI.class.getResourceAsStream("drop.png"));
                gui = new GameGUI();
                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gui.setTitle("Bitcoin Catcher");
                gui.setResizable(false);
                lastFrameTime = System.nanoTime();
                gui.setSize(900, 600);
                gui.setLocationRelativeTo(null);
                GameField gameField = new GameField();
                gui.add(gameField);
                gui.setVisible(true);


        }
        public static void onRepaint (Graphics g){
            g.drawImage(backgroundIMG, 0,0, null);
            long currentTime = System.nanoTime();
            float deltaTime = (currentTime - lastFrameTime) * 0.00000001f;
            lastFrameTime = currentTime;
            dropY = dropY + dropSpeed * deltaTime;
            g.drawImage(drop, (int) dropX, (int) dropY, null);

        }
        private static class GameField extends JPanel{
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                onRepaint(g);
                repaint();

            }
        }


}
