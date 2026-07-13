import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {
    private int row;
    private int column;
    private boolean toBeSolved;
    Tile(int row, int column){
        this.row = row;
        this.column = column;
        this.setBackground(new Color(250, 250, 255));
        this.setFont(new Font("Meiryo UI", Font.BOLD, 20));
        this.setForeground(new Color(44, 63, 112));
        this.setFocusable(false);
    }
    public int getRow(){return row;}
    public int getColumn(){return column;}
    public void setToBeSolved(boolean toBeSolved){this.toBeSolved = toBeSolved;}
    public boolean getToBeSolved(){return toBeSolved;}
}
