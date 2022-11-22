package bitcoinGUI;

import javax.swing.*;

public class GameGUI extends JFrame {
    private static GameGUI gui;
        private static void createWindow(){
            //Creating and setting up the game window
                gui = new GameGUI();
                gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
                gui.setTitle("Bitcoin Catcher");
                gui.setResizable(false);
                gui.setSize(900, 600);

        }

}
