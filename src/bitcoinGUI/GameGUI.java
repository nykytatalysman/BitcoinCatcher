package bitcoinGUI;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private static GameGUI gui;
        public static void createWindow(){
            //Creating and setting up the game window
                gui = new GameGUI();
                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gui.setTitle("Bitcoin Catcher");
                gui.setResizable(false);
                gui.setSize(900, 600);
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);

        }
        public static void onRepaint (Graphics g){

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
