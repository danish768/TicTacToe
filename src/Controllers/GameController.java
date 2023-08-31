package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Player;

import java.util.List;

public class GameController {
    //GameController will have all the methods that a client needs from the game.


    public void undo(){

    }

    public Game createGame(int dimension, List<Player> players){
        try{
            Game game = Game.getBuilder().setDimension(dimension).setPlayer(players).build();
            return game;
        } catch (Exception e){
            return null;
        }
    }

    public Player getWinner(){

    }

    public void displayBoard(){

    }

    public void executeNextMove(){

    }

    public GameStatus getGameStatus(){

    }

}

