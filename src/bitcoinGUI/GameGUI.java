package bitcoinGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameGUI extends JFrame {
    private static GameGUI gui;
    private static Image backgroundIMG;
    private static Image drop;
    private static Image gameOver;
    private static Image restartGame;
    private static long lastFrameTime;
    private static float dropY = -150;
    private static float dropX;
    private static float dropSpeed = 22;
    private static int score = 0;
    private static float restartX = 200;
    private static float restartY = 350;
        public static void createWindow() throws IOException {
            //Creating and setting up the game window
                backgroundIMG = ImageIO.read(GameGUI.class.getResourceAsStream("backgroundIMG.png"));
                gameOver = ImageIO.read(GameGUI.class.getResourceAsStream("gameOver.png"));
                drop = ImageIO.read(GameGUI.class.getResourceAsStream("drop.png"));
                restartGame = ImageIO.read(GameGUI.class.getResourceAsStream("restartGame.png"));
                gui = new GameGUI();
                JLabel scoreRecord = new JLabel("Your Score: " + score);
                scoreRecord.setSize(220, 150);
                scoreRecord.setPreferredSize(new Dimension(100, 25));
                scoreRecord.setFont(new Font("Your Score: " + score, Font.PLAIN, 10));
                scoreRecord.setOpaque(true);

                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gui.setTitle("Bitcoin Catcher");
                gui.setResizable(false);
                lastFrameTime = System.nanoTime();
                gui.setSize(900, 600);
                gui.setLocationRelativeTo(null);
                GameField gameField = new GameField();
                gameField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        int x = e.getX();
                        int y = e.getY();
                        float dropXRight = dropX + drop.getWidth(null);
                        float dropTBottom = dropY + drop.getHeight(null);
                        boolean isDrop = x >= dropX && x <= dropXRight && y >= dropY && y<= dropTBottom;
                            if(isDrop){
                                dropY = -100;
                                dropX = (int) (Math.random() * (gameField.getWidth() - drop.getHeight(null)));
                                gui.setTitle("Bitcoin Catcher | Your Score: " + score);
                                score++;
                                dropSpeed = dropSpeed + 3;
                            }
                            //Restart button logic;
                            float restartXLeft = restartX + restartGame.getWidth(null);
                            float restartYBottom = restartY + restartGame.getHeight(null);
                            boolean ifRestart = x >= restartX && x <= restartXLeft && y >= restartY && y <= restartYBottom;
                            if(ifRestart && dropY > gui.getHeight()){
                                dropY = -100;
                                dropX = (int) (Math.random() * (gameField.getWidth() - drop.getHeight(null)));
                                score = 0;
                                dropSpeed = 22;
                                gui.setTitle("Bitcoin Catcher | Your Score: " + score);
                            }
                    }
                });
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
                if(dropY > gui.getHeight()){
                    g.drawImage(gameOver, 25, 0, null);
                    g.drawImage(restartGame, (int)restartX, (int)restartY, null);
                }

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
