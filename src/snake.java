import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class snake implements ActionListener {

    mainEngine me;
    body b;
    dirs d;
    Timer timer;
    static Image image_head_r, image_head_l, image_head_u, image_head_d, image_body, image_feed;

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

    snake(mainEngine me){
        this.me = me;
        b = new body();
        cell c;
        for(int i = 0; i <= 2; i++){
            c = me.f[i][mainWindow.FIELD_SIZE / 2];
            c.v = vals.SNAKE;
            c.d = dirs.RIGHT;
            b.setHead(c,i);
        }
        d = dirs.RIGHT;
        timer = new Timer(mainWindow.ONE_TICK * 3,this);
        timer.start();
    }

    public void actionPerformed(ActionEvent ae){

        cell c;
        c = b.getHead();

        int x = c.x;
        int y = c.y;

        switch (d) {
            case RIGHT -> x++;
            case LEFT -> x--;
            case UP -> y--;
            case DOWN -> y++;
        }

        if(x < 0 || y < 0 || x == mainWindow.FIELD_SIZE || y == mainWindow.FIELD_SIZE){
            me.gameOver();
            return;
        }

        if(me.f[x][y].v == vals.SNAKE || me.f[x][y].v == vals.FEED){
            me.gameOver();
            return;
        }

        c = me.f[x][y];
        c.d = d;
        if(c.v == vals.APPLE) {
            c.v = vals.FEED;
            me.findAndRelocateApple(c);
        } else {
            c.v = vals.SNAKE;
        }

        b.moveHead(c);

        c = b.getTail();

        if(c.v == vals.FEED) {
            c.v = vals.SNAKE;
        } else {
            c.v = vals.EMPTY;
            b.moveTail();
        }

        me.draw();

    }

    void draw(Graphics g){

        Image im;
        boolean f = true;

//        cell_Iterator i = new cell_Iterator(b);
//        cell c;
//        while (i.hasNext()){
//            c = i.next();
//            if(f){
//                switch (c.d){
//                    case RIGHT -> im = image_head_r;
//                    case LEFT -> im = image_head_l;
//                    case UP -> im = image_head_u;
//                    default -> im = image_head_d;
//                }
//                f = false;
//            } else{
//                switch (c.v){
//                    case FEED -> im = image_feed;
//                    default -> im = image_body;
//                }
//            }
//            g.drawImage(im, c.x * mainWindow.CELL_SIZE, c.y * mainWindow.CELL_SIZE, null);
//        }

        for(cell c : new cell_Iterable(b)){
            if(f){
                switch (c.d){
                    case RIGHT -> im = image_head_r;
                    case LEFT -> im = image_head_l;
                    case UP -> im = image_head_u;
                    default -> im = image_head_d;
                }
                f = false;
            } else{
                switch (c.v){
                    case FEED -> im = image_feed;
                    default -> im = image_body;
                }
            }
            g.drawImage(im, c.x * mainWindow.CELL_SIZE, c.y * mainWindow.CELL_SIZE, null);
        }

    }

}
