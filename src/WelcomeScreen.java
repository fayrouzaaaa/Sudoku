import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame {
    WelcomeScreen(){
        this.setTitle("Welcome!");
        this.setSize(550,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
        displayWelcomeScreen();
        this.setVisible(true);
    }

    public void displayWelcomeScreen(){
        JLabel welcomeText = new JLabel("Welcome to Sudoku!");
        welcomeText.setBounds(65, -40, 500, 300);
        welcomeText.setFont(new Font("Meiryo UI", Font.PLAIN, 40));
        welcomeText.setForeground(new Color(44, 63, 112));
        this.add(welcomeText);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Meiryo UI", Font.BOLD, 20));
        playButton.setBounds(220, 200, 100, 50);
        playButton.setBackground(new Color(44, 63, 112));
        playButton.setForeground(new Color(232, 235, 237));
        playButton.setFocusable(false);
        playButton.addActionListener(e->playAction());
        this.add(playButton);
    }

    public void playAction(){
        GameManager gameManager = new GameManager();
        this.dispose();
    }
}
