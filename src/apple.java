import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class apple implements ActionListener {

    mainEngine mainEngine;
    cell body;
    Timer timer;
    static Image image;

    static {
        ImageIcon iid = new ImageIcon("apple.png");
        image = iid.getImage();
    }

    apple(mainEngine me){

        mainEngine = me;
        timer = new Timer(mainWindow.ONE_TICK * 20,this);

        locateApple();

    }

    apple(mainEngine me, int x, int y){

        mainEngine = me;
        body = mainEngine.f[x][y];
        body.z = cells_vals.APPLE;

    }

    public void locateApple(){
        Random r = new Random();
        body = mainEngine.f[r.nextInt(mainWindow.FIELD_SIZE)][r.nextInt(mainWindow.FIELD_SIZE)];
        body.z = cells_vals.APPLE;

        timer.restart();
    }

    public void actionPerformed(ActionEvent ae){
        body.z = cells_vals.EMPTY;
        locateApple();

        mainEngine.draw();
    }

    public void draw(Graphics g){
        g.drawImage(image, body.x * mainWindow.CELL_SIZE, body.y * mainWindow.CELL_SIZE, null);
    }

}
