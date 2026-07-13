import java.util.ArrayList;
import java.util.Random;

public class GridGenerator {
    private int[][] solution;
    private Random random;
    private int[][] count;

    GridGenerator(){
        solution = new int[9][9];
        count = new int[9][9];
        random = new Random();
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                solution[i][j] = 0;
                count[i][i] = 0;
            }
        }
    }

    public int[][] getSolution(){
        int[][] copy = new int[9][9];
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                copy[i][j] = solution[i][j];
            }
        }
        return copy;
    }

    public boolean isInRow(int row, int target, int[][] grid){
        for (int j=0; j<9; j++){
            if (grid[row][j]==target){
                return true;
            }
        }
        return false;
    }

    public boolean isInColumn(int column, int target, int[][] grid){
        for (int i=0; i<9; i++){
            if (grid[i][column]==target){
                return true;
            }
        }
        return false;
    }

    public boolean isInGrid(int row, int column, int target, int[][] grid){
        int startRow = row - row%3;
        int startColumn = column - column%3;
        for(int i=startRow; i<startRow+3; i++){
            for (int j=startColumn; j<startColumn+3; j++){
                if (grid[i][j]==target){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidPosition(int row, int column, int target, int[][] grid){
        if ((isInGrid(row, column, target, grid)) || (isInRow(row, target, grid)) || (isInColumn(column, target, grid))){
            return false;
        }
        return true;
    }

    public boolean generateSolution(int row, int column){
        ArrayList<Integer> attemptedNumbers = new ArrayList<>();

        if (row==9)
            return true;
        if (column==9)
            return generateSolution(row+1, 0);

        if(count[row][column]==9-column){
            count[row][column] = 0;
            if (column==0) {
                solution[row-1][8]=0;
                return generateSolution(row - 1, 8);
            }
            else {
                solution[row][column-1] = 0;
                return generateSolution(row, column - 1);
            }
        }

        while (attemptedNumbers.size() < 10){
            int number = random.nextInt(9)+1;
            if (!attemptedNumbers.contains((Integer)number) && isValidPosition(row, column, number, solution)){
                solution[row][column] = number;
                count[row][column]++;
                break;
            }
            else {
                attemptedNumbers.add((Integer)number);
            }
        }

        if(solution[row][column] == 0){
            if (column==0) {
                solution[row-1][8]=0;
                return generateSolution(row - 1, 8);
            }
            else {
                solution[row][column-1] = 0;
                return generateSolution(row, column - 1);
            }
        }

        else {
            generateSolution(row, column+1);
        }
        return false;
    }
}