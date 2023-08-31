package Models;

import Exceptions.InvalidDimensionException;
import Exceptions.InvalidNumberOfPlayers;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> player;
    private List<Move> move;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public List<Move> getMove() {
        return move;
    }

    public void setMove(List<Move> move) {
        this.move = move;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static class GameBuilder{
        private int dimension;
        private List<Player> player;

        public int getDimension() {
            return dimension;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayer() {
            return player;
        }

        public GameBuilder setPlayer(List<Player> player) {
            this.player = player;
            return this;
        }

        private boolean isValid() throws InvalidDimensionException, InvalidNumberOfPlayers {
            //Game validations will come here.
            if(this.dimension < 3){
                throw new InvalidDimensionException("Dimension can't be less than 3");
            }

            if(this.player.size() != dimension-1){
                throw new InvalidNumberOfPlayers("Number of players should be 1 less than the dimensions");
            }

            //Check if each player has different symbol or not.
            return true;
        }

        public Game build(){
            //Validation
            try {
                isValid();
            } catch (Exception exception){
                System.out.println("Exception occured during Game creation");
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayer(player);
            game.setMove(new ArrayList<>());
            game.setNextPlayerIndex(0);

            return game;
        }
    }


}



