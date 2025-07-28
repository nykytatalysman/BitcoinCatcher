package bitcoinGUI;

import java.io.*;

public class ScoreManager {
    private static final String SCORE_FILE = "scoreFile.txt";

    public static int loadHighScore() {
        File file = new File(SCORE_FILE);
        if (!file.exists()) {
            saveHighScore(0);
            return 0;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void saveHighScore(int score) {
        try (FileWriter writer = new FileWriter(SCORE_FILE)) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
