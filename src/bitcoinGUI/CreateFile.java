package bitcoinGUI;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CreateFile {

    public static void createScoreFile(String filePath) {
        File scoreFile = new File(filePath);

        try {
            if (scoreFile.createNewFile()) {
                showMessage("File created: " + scoreFile.getName());
            } else {
                showMessage("File already exists.");
            }
        } catch (IOException e) {
            showMessage("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "File Status", JOptionPane.INFORMATION_MESSAGE);
    }

    // For backward compatibility, this overloaded method calls the parameterized one.
    public static void createScoreFile() {
        createScoreFile("scoreFile.txt");
    }
}
