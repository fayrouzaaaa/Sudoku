import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame{
    private GameManager gameManager;
    GameOverScreen(GameManager gameManager){
        this.setTitle("Game Over!");
        this.setSize(550,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.gameManager = gameManager;
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
        displayGameOverScreen();
        this.setVisible(true);
    }

    public void displayGameOverScreen(){
        JLabel gameOverText = new JLabel("Game Over!");
        gameOverText.setBounds(150,-60,500,300);
        gameOverText.setFont(new Font("Meiryo UI", Font.PLAIN, 40));
        gameOverText.setForeground(new Color(44,63,112));
        this.add(gameOverText);

        if (gameManager.isWinner()){
            JLabel winText = new JLabel("You win :D");
            winText.setBounds(190,0,500,300);
            winText.setFont(new Font("Meiryo UI", Font.PLAIN, 30));
            winText.setForeground(new Color(44,63,112));
            this.add(winText);
        }
        else {
            JLabel loseText = new JLabel("You lose :(");
            loseText.setBounds(190,0,500,300);
            loseText.setFont(new Font("Meiryo UI", Font.PLAIN, 30));
            loseText.setForeground(new Color(44,63,112));
            this.add(loseText);
        }

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
        newGameButton.setBounds(100, 240, 150, 50);
        newGameButton.setBackground(new Color(44,63,112));
        newGameButton.setForeground(new Color(232,235,237));
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(e->newGame());
        this.add(newGameButton);

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Meiryo UI", Font.PLAIN, 20));
        restartButton.setBounds(300, 240, 150, 50);
        restartButton.setBackground(new Color(232,235,237));
        restartButton.setForeground(new Color(44,63,112));
        restartButton.setFocusable(false);
        restartButton.addActionListener(e->restart());
        this.add(restartButton);
    }
    public void newGame(){
        gameManager.startNewGame();
        this.dispose();
    }
    public void restart(){
        gameManager.restartGame();
        this.dispose();
    }
}
