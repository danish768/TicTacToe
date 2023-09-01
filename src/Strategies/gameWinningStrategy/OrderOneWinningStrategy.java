package Strategies.gameWinningStrategy;

import Models.Board;
import Models.Cell;
import Models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy{

    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();

    private HashMap<Character,Integer> topLeftDiagonal = new HashMap<>();
    private HashMap<Character,Integer> topRightDiagonal = new HashMap<>();

    public OrderOneWinningStrategy(int dimension){
        for(int i = 0;i < dimension;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    private boolean isCellOnTopLeftDiagonal(int row,int col){
        //Check if the cell is lying on the top left diagonal.
        return row==col;
    }

    private boolean isCellOnTopRightDiagonal(int row,int col, int dimension){
        //Check if the cell is lying on the top right diagonal
        return row + col == dimension-1;
    }

    @Override
    public boolean checkWinner(Board board, Player player, Cell moveCell) {
        char symbol = player.getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int dimension = board.getBoard().size();

        //Increment the symbol count for the row, col and diagonal (if the index lies on the diagonal).
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }
        rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).get(symbol)+1);

        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }
        colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).get(symbol)+1);

        if(isCellOnTopLeftDiagonal(row,col)){
            if(!topLeftDiagonal.containsKey(symbol)){
                topLeftDiagonal.put(symbol,0);
            }
            topLeftDiagonal.put(symbol,topLeftDiagonal.get(symbol)+1);
        }

        if(isCellOnTopRightDiagonal(row,col,dimension)){
            if(!topRightDiagonal.containsKey(symbol)){
                topRightDiagonal.put(symbol,0);
            }
            topRightDiagonal.put(symbol,topRightDiagonal.get(symbol)+1);
        }

        if(rowSymbolCount.get(row).get(symbol) == dimension ||
                colSymbolCount.get(col).get(symbol) == dimension){
            return true;
        }

        if(isCellOnTopLeftDiagonal(row, col)){
            return topLeftDiagonal.get(symbol) == dimension;
        }

        if(isCellOnTopRightDiagonal(row, col, dimension)){
            return topRightDiagonal.get(symbol) == dimension;
        }

        return false;
    }
}
