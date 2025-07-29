package bitcoinGUI;

/**
 * Simple player profile storing the player's name and high score.
 */
public class PlayerProfile {
    private final String name;
    private int highScore;

    public PlayerProfile(String name) {
        this.name = name;
        this.highScore = ScoreManager.loadHighScore(name);
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void updateHighScore(int newScore) {
        if (newScore > highScore) {
            highScore = newScore;
            ScoreManager.saveHighScore(name, highScore);
        }
    }
}
