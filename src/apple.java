import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class apple implements ActionListener {

    mainEngine me;
    cell c;
    Timer timer;
    int blinks;
    static Image image;

    static {
        ImageIcon iid = new ImageIcon("apple.png");
        image = iid.getImage();
    }

    apple(mainEngine me){
        this.me = me;
        timer = new Timer(0,this);
        timer.setRepeats(false);
        locateApple();
    }

    void locateApple(){
        Random r = new Random();
        do {
            c = me.f[r.nextInt(mainWindow.FIELD_SIZE)][r.nextInt(mainWindow.FIELD_SIZE)];
        } while (c.v != vals.EMPTY);
        c.v = vals.APPLE;
        blinks = 0;
        timer.setInitialDelay(mainWindow.ONE_TICK * 100);
        timer.restart();
    }

    public void actionPerformed(ActionEvent ae){
        if(blinks==6){
            c.v = vals.EMPTY;
            locateApple();
        } else {
            blinks++;
            timer.setInitialDelay(mainWindow.ONE_TICK * 5);
            timer.start();
        }
        me.draw();
    }

    void draw(Graphics g){
        if (blinks % 2 == 0) g.drawImage(image, c.x * mainWindow.CELL_SIZE, c.y * mainWindow.CELL_SIZE, null);
    }

}
