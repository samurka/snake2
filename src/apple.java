import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class apple implements ActionListener {

    private final static Image image;
    private final mainEngine me;
    private final Timer timer;
    private cell c;
    private int blinks;

    static {
        ImageIcon iid = new ImageIcon("apple.png");
        image = iid.getImage();
    }

    apple(mainEngine me) {
        this.me = me;
        timer = new Timer(0, this);
        timer.setRepeats(false);
        locateApple();
    }

    private void locateApple() {
        c = me.getRandomEmptyCell();
        c.setV(vals.APPLE);
        blinks = 0;
        timer.setInitialDelay(mainWindow.ONE_TICK * 100);
        timer.restart();
    }

    public boolean relocateIfEqual(cell c) {
        if (c.equals(this.c)) {
            locateApple();
            return true;
        } else {
            return false;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (blinks == 6) {
            c.setV(vals.EMPTY);
            locateApple();
        } else {
            blinks++;
            timer.setInitialDelay(mainWindow.ONE_TICK * 5);
            timer.start();
        }
        me.draw();
    }

    public void draw(Graphics g) {
        if (blinks % 2 == 0) c.draw(g, image);
    }

    public void stopTimer() {
        timer.stop();
    }

}
