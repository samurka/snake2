import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class snake implements ActionListener {

    private final static Image image_head_r, image_head_l, image_head_u, image_head_d, image_body, image_feed;
    private final mainEngine me;
    private final Timer timer;
    private final body b;
    private dirs d; // next direction
    private dirs h; // head direction

    static {
        ImageIcon iid;
        iid = new ImageIcon("snake_0.png");
        image_body = iid.getImage();
        iid = new ImageIcon("snake_1.png");
        image_feed = iid.getImage();
        iid = new ImageIcon("snake_r.png");
        image_head_r = iid.getImage();
        iid = new ImageIcon("snake_l.png");
        image_head_l = iid.getImage();
        iid = new ImageIcon("snake_u.png");
        image_head_u = iid.getImage();
        iid = new ImageIcon("snake_d.png");
        image_head_d = iid.getImage();
    }

    snake(mainEngine me) {
        this.me = me;
        b = new body(me.getInitialSnakeCells());
        for (cell c : new cell_Iterable(b)) {
            c.setV(vals.SNAKE);
        }
        d = h = dirs.RIGHT;
        timer = new Timer(mainWindow.ONE_TICK * 3, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent ae) {

        cell c;
        c = me.tryMoveHead(b.getCell(b.getHead()), d);

        if (c != null) {
            if (c.getV() == vals.APPLE) {
                c.setV(vals.FEED);
                me.findAndRelocateApple(c);
            } else {
                c.setV(vals.SNAKE);
            }
            h = d;
            b.moveHead(c);
        } else {
            return;
        }

        c = b.getCell(b.getTail());

        if (c.getV() == vals.FEED) {
            c.setV(vals.SNAKE);
        } else {
            c.setV(vals.EMPTY);
            b.moveTail();
        }
        me.draw();

    }

    void draw(Graphics g) {

        Image im;
        boolean f = true;

        for (cell c : new cell_Iterable(b)) {
            if (f) {
                switch (h) {
                    case RIGHT -> im = image_head_r;
                    case LEFT -> im = image_head_l;
                    case UP -> im = image_head_u;
                    default -> im = image_head_d;
                }
                f = false;
            } else {
                switch (c.getV()) {
                    case FEED -> im = image_feed;
                    default -> im = image_body;
                }
            }
            c.draw(g, im);
        }

    }

    public void changeDir(int key) {
        if (key == KeyEvent.VK_LEFT && h != dirs.RIGHT) {
            d = dirs.LEFT;
        } else if (key == KeyEvent.VK_RIGHT && h != dirs.LEFT) {
            d = dirs.RIGHT;
        } else if (key == KeyEvent.VK_UP && h != dirs.DOWN) {
            d = dirs.UP;
        } else if (key == KeyEvent.VK_DOWN && h != dirs.UP) {
            d = dirs.DOWN;
        }
    }

    public void stopTimer() {
        timer.stop();
    }

}
