import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class apple implements ActionListener {

    cell[][] field;
    cell body;
    Timer timer;
    static Image image;

    static {
        ImageIcon iid = new ImageIcon("apple.png");
        image = iid.getImage();
    }

    apple(cell[][] f){

        field = f;
        timer = new Timer(2500,this);

        locateApple();

    }

    public void locateApple(){
        Random r = new Random();
        body = field[r.nextInt(MainWindow.FIELD_SIZE)][r.nextInt(MainWindow.FIELD_SIZE)];
        body.z = cells_vals.APPLE;

        timer.restart();
    }

    public void actionPerformed(ActionEvent ae){
        body.z = cells_vals.EMPTY;
        locateApple();
    }

    public void feedApple(){
        locateApple();
    }

    public void draw(Graphics g){
        g.drawImage(image, body.x * MainWindow.CELL_SIZE, body.y * MainWindow.CELL_SIZE, null);
    }

}
