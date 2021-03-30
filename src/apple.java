import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class apple implements ActionListener {

    mainEngine mainEngine;
    cell body;
    Timer timer;
    int blinks;
    static Image image;

    static {
        ImageIcon iid = new ImageIcon("apple.png");
        image = iid.getImage();
    }

    apple(mainEngine me){

        mainEngine = me;
        timer = new Timer(0,this);
        timer.setRepeats(false);

        locateApple();

    }

    apple(mainEngine me, int x, int y){

        mainEngine = me;
        body = mainEngine.f[x][y];
        body.z = cells_vals.APPLE;

    }

    public void locateApple(){
        Random r = new Random();
        do {
            body = mainEngine.f[r.nextInt(mainWindow.FIELD_SIZE)][r.nextInt(mainWindow.FIELD_SIZE)];
        } while (body.z != cells_vals.EMPTY);
        body.z = cells_vals.APPLE;

        blinks = 0;
        timer.setInitialDelay(mainWindow.ONE_TICK * 100);
        timer.restart();
    }

    public void actionPerformed(ActionEvent ae){
        if(blinks==6){
            body.z = cells_vals.EMPTY;
            locateApple();
        } else {
            blinks++;
            timer.setInitialDelay(mainWindow.ONE_TICK * 5);
            timer.start();
        }

        mainEngine.draw();
    }

    public void draw(Graphics g){
        if(blinks % 2 == 0) {
            g.drawImage(image, body.x * mainWindow.CELL_SIZE, body.y * mainWindow.CELL_SIZE, null);
        }
    }

}
