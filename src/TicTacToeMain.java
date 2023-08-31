import Controllers.GameController;
import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class TicTacToeMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("What should be the dimension of the game ?");
        int dimension = sc.nextInt();

        System.out.println("Will there be any Bot in the game ? y/n");
        String isBot = sc.next();

        int numberOfPlayers = dimension - 1;
        int numberOfHumanPlayers = numberOfPlayers;

        List<Player> players = new ArrayList<>();

        if(isBot.charAt(0)=='y') {
            numberOfHumanPlayers=-1;

            System.out.println("Enter the name of the Bot");
            String botName = sc.next();

            System.out.println("Enter the Bot Symbol");
            String symbol = sc.next();

            players.add(new Bot(botName,symbol.charAt(0), PlayerType.BOT, BotDifficultyLevel.EASY));
        }

        for(int i =0;i < numberOfHumanPlayers;i++){
            System.out.println("Enter the name of the player");
            String name = sc.next();

            System.out.println("Enter the player symbol");
            String symbol = sc.next();

            players.add(new Player(name,symbol.charAt(0),PlayerType.HUMAN));
        }

        Game game =gameController.createGame(dimension,players);

        //Start playing

        while (gameController.getGameStatus().equals(GameStatus.IN_PROGRESS)){
            //While the game status is IN_PROGRESS, keep playing the game.

        }
    }
}