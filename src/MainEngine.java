import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainEngine extends JPanel implements ActionListener {

    cell[][] f;
    snake s;
    apple a1, a2;

    MainEngine(){

        f = emptyField();
        s = new snake(f);
        a1 = new apple(f);
        a2 = new apple(f);

        setBackground(Color.BLACK);

        Timer timer = new Timer(100,this);
        timer.start();
    }

    public cell[][] emptyField(){
        f = new cell[MainWindow.FIELD_SIZE][MainWindow.FIELD_SIZE];
        for (int i = 0; i < MainWindow.FIELD_SIZE; i++){
            for(int j = 0; j < MainWindow.FIELD_SIZE; j++){
                f[i][j] = new cell(i,j,cells_vals.EMPTY);
            }
        }
        return f;
    }

    public void actionPerformed(ActionEvent ae){
        if(s.inGame){
            repaint();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        s.draw(g);
        a1.draw(g);
        a2.draw(g);
    }

}
