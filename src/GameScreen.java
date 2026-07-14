import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameScreen extends JFrame {
    private JButton selectedNumberButton;
    private JLabel mistakes;
    private Tile selectedTile;
    private int selectedNumber = -1;
    private int selectedRow = -1;
    private int selectedColumn = -1;
    private GameManager gameManager;

    GameScreen(){
        this.setTitle("Sudoku");
        this.setSize(600, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
    }

    public void setGameManager(GameManager gameManager){this.gameManager = gameManager;}

    public void updateMistakeCounter(String mistakeCount){
        mistakes.setText("Mistakes: "+ mistakeCount+ "/3");
    }

    public void displayTopPanel(){
        mistakes = new JLabel ("Mistakes: 0/3");
        mistakes.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
        mistakes.setForeground(new Color(44, 63, 112));
        mistakes.setBounds(50,10,200,50);
        this.add(mistakes);
    }
    public void displayBoardPanel(int[][] puzzle){
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(9,9));
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                Tile tile = new Tile(i,j);
                if (puzzle[i][j]!=0) {
                    tile.setText(String.valueOf(puzzle[i][j]));
                    tile.setToBeSolved(false);
                }
                else tile.setToBeSolved(true);
                setBoardBorders(i, j, tile);
                tile.addActionListener(e -> selectTile(e));
                board.add(tile);
            }
        }
        board.setBounds(50,60,500,500);
        this.add(board);
    }

    public void displayChoicesPanel(){
        JPanel choices = new JPanel();
        choices.setLayout(new GridLayout(1,9));
        for (int i=0; i<9; i++){
            JButton choice = new JButton(String.valueOf(i+1));
            choice.setBackground(new Color(245, 250, 255));
            choice.setFont(new Font("Meiryo UI", Font.BOLD, 20));
            choice.setForeground(new Color(44, 63, 112));
            choice.setFocusable(false);
            choice.addActionListener(e->selectNumber(e));
            choices.add(choice);
        }
        choices.setBounds(50, 580, 500,50);
        this.add(choices);
    }

    public void setBoardBorders(int i, int j, JButton tile){
        if ((i==0 && j==0) || (i==3 && j==0) || (i==6 && j==0)
                || (i==0 && j==3) || (i==3 && j==3) || (i==6 && j==3)
                || (i==0 && j==6) || (i==3 && j==6) || (i==6 && j==6))
            tile.setBorder(BorderFactory.createMatteBorder(5,5,1,1, new Color(44,63,112)));
        else if (i==8 && (j==0 || j==3 || j==6))
            tile.setBorder(BorderFactory.createMatteBorder(1,5,5,1, new Color(44,63,112)));
        else if (j==8 && (i==0 || i==3 || i==6))
            tile.setBorder(BorderFactory.createMatteBorder(5,1,1,5, new Color(44,63,112)));
        else if (i==8 && j==8)
            tile.setBorder(BorderFactory.createMatteBorder(1,1,5,5, new Color(44,63,112)));
        else if (i==0 || i==3 || i==6)
            tile.setBorder(BorderFactory.createMatteBorder(5,1,1,1, new Color(44,63,112)));
        else if (i==8)
            tile.setBorder(BorderFactory.createMatteBorder(1,1,5,1, new Color(44,63,112)));
        else if (j==0 || j==3 || j==6)
            tile.setBorder(BorderFactory.createMatteBorder(1,5,1,1, new Color(44,63,112)));
        else if (j==8)
            tile.setBorder(BorderFactory.createMatteBorder(1,1,1,5, new Color(44,63,112)));
        else
            tile.setBorder(BorderFactory.createLineBorder(new Color(44,63,112), 1));
    }

    public void selectNumber(ActionEvent e){
        JButton choice = (JButton) e.getSource();
        if (selectedNumberButton != null){
            selectedNumberButton.setBackground(new Color(245, 250, 255));
        }
        if (selectedNumberButton == choice){
            selectedNumberButton.setBackground(new Color(245, 250, 255));
            selectedNumberButton = null;
            selectedNumber = -1;
            return;
        }
        selectedNumberButton = choice;
        selectedNumberButton.setBackground(new Color(147, 167, 243));
        selectedNumber = Integer.valueOf(selectedNumberButton.getText());
    }

    public void selectTile(ActionEvent e){
        Tile tile = (Tile) e.getSource();
        if (selectedTile!=null){
            selectedTile.setBackground(new Color (250, 250, 255));
        }
        if (selectedTile == tile){
            selectedTile.setBackground(new Color(250, 250, 255));
            if (Integer.valueOf(selectedTile.getText())==selectedNumber)
                selectedTile.setText("");
            selectedRow = -1;
            selectedColumn = -1;
            selectedTile = null;
            return;
        }
        selectedTile = tile;
        selectedTile.setBackground(new Color(147,167,243));
        selectedRow = selectedTile.getRow();
        selectedColumn = selectedTile.getColumn();

        if(selectedNumber != -1 && selectedTile.getToBeSolved()){
            selectedTile.setText(String.valueOf(selectedNumber));
            if (gameManager.isCorrectChoice()){
                selectedTile.setForeground(new Color(78, 112, 159, 255));
                selectedTile.setToBeSolved(false);
            }
            else selectedTile.setForeground(new Color(147, 5, 0));
        }
    }

    public int getSelectedNumber(){return selectedNumber;}
    public int getSelectedRow(){return selectedRow;}
    public int getSelectedColumn(){return selectedColumn;}
}
