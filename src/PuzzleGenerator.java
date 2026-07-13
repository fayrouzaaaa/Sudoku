import java.util.Random;

public class PuzzleGenerator {
    private GridGenerator gridGenerator;
    private int[][] puzzle;
    private Random random;

    PuzzleGenerator(GridGenerator gridGenerator){
        this.gridGenerator = gridGenerator;
        puzzle = gridGenerator.getSolution();
        random = new Random();
    }

    public int[][] getPuzzle(){
        int[][] copy = new int[9][9];
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                copy[i][j] = puzzle[i][j];
            }
        }
        return copy;
    }

    public boolean solvePuzzle(int row, int column){
        int count =0;
        for (int i=0; i<9; i++){
            if (gridGenerator.isValidPosition(row, column, i, puzzle))
                count++;
        }
        if (count!=1)
            return false;
        return true;
    }

    public void generatePuzzle(int difficulty){
        while (difficulty>0){
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            if (puzzle[row][column]==0)
                continue;
            int removed = puzzle[row][column];
            puzzle[row][column] = 0;
            if (solvePuzzle(row, column))
                difficulty--;
            else
                puzzle[row][column] = removed;
        }
    }
}
