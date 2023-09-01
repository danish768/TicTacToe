package Models;

import Exceptions.InvalidDimensionException;
import Exceptions.InvalidNumberOfPlayers;
import Strategies.gameWinningStrategy.GameWinningStrategy;
import Strategies.gameWinningStrategy.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> player;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void displayBoard(){
        this.board.displayBoard();
    }

    public void makeNextMove(){
        //1. Get the next player to move.
        Player currentMovePlayer = player.get(nextPlayerIndex);

        //2. Player should decide the move.
        Move move = currentMovePlayer.decideMove(this.getBoard());

        //3. Validate the move.
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        while(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            System.out.println("The cell is filled already, Please make a move on empty cell");
            move = currentMovePlayer.decideMove(this.getBoard());
            row = move.getCell().getRow();
            col = move.getCell().getCol();
        }


        //4. If the move is valid, then make the move.
        System.out.println("Move happened at (" + row+", "+col+")");
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(currentMovePlayer);

        //5. Add this move in the final list of Moves.
        moves.add(move);

        //6. Check if the player has WON the game.
        if(gameWinningStrategy.checkWinner(board, currentMovePlayer, move.getCell())){
            gameStatus= GameStatus.ENDED;
            winner= currentMovePlayer;
        }

        //7. Move to the next Player.
        nextPlayerIndex+=1;
        nextPlayerIndex%=player.size();

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
        return moves;
    }

    public void setMove(List<Move> moves) {
        this.moves = moves;
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

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
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
                System.out.println("Exception occurred during Game creation");
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayer(player);
            game.setMove(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));

            return game;
        }
    }
}



