package bitcoinGUI;

import java.io.*;

/**
 * Manages saving and loading of high scores per player profile.
 */
public class ScoreManager {

    private static File scoresDir() {
        File dir = new File("scores");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private static File fileFor(String playerName) {
        String safeName = playerName.replaceAll("[^a-zA-Z0-9_-]", "_");
        return new File(scoresDir(), "score_" + safeName + ".txt");
    }

    public static int loadHighScore(String playerName) {
        File file = fileFor(playerName);
        if (!file.exists()) {
            saveHighScore(playerName, 0);
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

    public static void saveHighScore(String playerName, int score) {
        File file = fileFor(playerName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
