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

                gui = new GameGUI();
                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gui.setTitle("Bitcoin Catcher");
                gui.setResizable(false);
                gui.setSize(900, 600);
                gui.setLocationRelativeTo(null);
                GameField gameField = new GameField();
                gui.add(gameField);
                gui.setVisible(true);


        }
        public static void onRepaint (Graphics g){
            g.drawImage(backgroundIMG, 0,0, null);
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
