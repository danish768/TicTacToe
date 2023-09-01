package Strategies.gameWinningStrategy;

import Models.Board;
import Models.Cell;
import Models.Player;

public interface GameWinningStrategy {

    boolean checkWinner(Board board, Player player, Cell moveCell);
}
