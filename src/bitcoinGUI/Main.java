package bitcoinGUI;

// Polataico Nichita

import javax.swing.JOptionPane;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            initializeGame();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to initialize the game: " + e.getMessage(),
                    "Initialization Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static void initializeGame() throws IOException {
        CreateFile.createScoreFile();
        GameGUI.createWindow();
    }
}
