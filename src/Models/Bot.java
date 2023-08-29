package Models;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, char symbol, PlayerType type,BotDifficultyLevel difficultyLevel) {
        super(name, symbol, type);
        this.botDifficultyLevel = difficultyLevel;
    }
}
