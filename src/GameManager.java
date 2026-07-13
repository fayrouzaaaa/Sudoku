public class GameManager {
    int difficulty;
    int correctAnswers;
    int wrongAnswers;
    int[][] solutionBoard;
    int[][] puzzleBoard;
    private GridGenerator gridGenerator;
    private PuzzleGenerator puzzleGenerator;
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;

    GameManager(){
        difficulty = 36;
        startNewGame();
    }

    public void generateSudoku(){
        gridGenerator = new GridGenerator();
        gridGenerator.generateSolution(0,0);
        solutionBoard = gridGenerator.getSolution();
        puzzleGenerator = new PuzzleGenerator(gridGenerator);
        puzzleGenerator.generatePuzzle(difficulty);
        puzzleBoard = puzzleGenerator.getPuzzle();
    }

    public void setupGameScreen(){
        gameScreen = new GameScreen();
        gameScreen.setGameManager(this);
        gameScreen.displayTopPanel();
        gameScreen.displayBoardPanel(puzzleBoard);
        gameScreen.displayChoicesPanel();
        gameScreen.setVisible(true);
    }

    public boolean isCorrectChoice(){
        int selectedNumber = gameScreen.getSelectedNumber();
        int row = gameScreen.getSelectedRow();
        int column = gameScreen.getSelectedColumn();

        boolean isCorrect = (selectedNumber == solutionBoard[row][column]);
        updateState(isCorrect);

        return isCorrect;
    }

    public void updateState(boolean isCorrect){
        if (isCorrect)
            correctAnswers++;
        else {
            wrongAnswers++;
            gameScreen.updateMistakeCounter(String.valueOf(wrongAnswers));
        }
        if (isGameOver()){
            gameOverScreen = new GameOverScreen(this);
            gameScreen.dispose();
        }
    }

    public boolean isWinner(){return correctAnswers==difficulty;}
    public boolean isLoser() {return wrongAnswers==3;}
    public boolean isGameOver() {return isWinner() || isLoser();}

    public void startNewGame(){
        correctAnswers = 0;
        wrongAnswers = 0;
        generateSudoku();
        setupGameScreen();
    }

    public void restartGame(){
        correctAnswers= 0;
        wrongAnswers = 0;
        setupGameScreen();
    }

}