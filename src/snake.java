import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class snake implements ActionListener {

    mainEngine mainEngine;
    cell[] body;
    int body_head, body_tail;
    dir_vals cur_dir;
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

        mainEngine = me;
        cur_dir = dir_vals.RIGHT;
        timer = new Timer(mainWindow.ONE_TICK * 3,this);

        body = new cell[10];
        body_head = 2;
        body_tail = 0;
        for(int i = 0; i <= body_head; i++){
            cell c = mainEngine.f[i][mainWindow.FIELD_SIZE / 2];
            c.z = cells_vals.SNAKE;
            c.dir_in = dir_vals.RIGHT;
            body[i] = c;
        }

        timer.start();

    }

    public void actionPerformed(ActionEvent ae){

        int x = body[body_head].x;
        int y = body[body_head].y;

        switch (cur_dir) {
            case RIGHT -> x++;
            case LEFT -> x--;
            case UP -> y--;
            case DOWN -> y++;
        }

        if(x < 0 || y < 0 || x == mainWindow.FIELD_SIZE || y == mainWindow.FIELD_SIZE){
            mainEngine.gameOver();
            return;
        }

        if(mainEngine.f[x][y].z == cells_vals.SNAKE || mainEngine.f[x][y].z == cells_vals.FEED){
            mainEngine.gameOver();
            return;
        }

        body_head++;

        if(body_head == body_tail || body_tail == 0 && body_head == body.length){
            growBody();
        }

        if(body_head == body.length){
            body_head=0;
        }

        cell c = mainEngine.f[x][y];
        if(c.z == cells_vals.APPLE) {
            c.z = cells_vals.FEED;
            mainEngine.findAndRelocateApple(c);
        } else {
            c.z = cells_vals.SNAKE;
        }
        body[body_head] = c;
        c.dir_in = cur_dir;

        c = body[body_tail];
        if(c.z == cells_vals.FEED) {
            c.z = cells_vals.SNAKE;
        } else {
            c.z = cells_vals.EMPTY;
            body_tail++;
            if(body_tail == body.length){
                body_tail=0;
            }
        }

        mainEngine.draw();

    }

    public void growBody(){
        cell[] body2 = new cell[body.length*2];
        int i = body_tail;
        int j = 0;
        while (true){
            body2[j] = body[i];
            if(i == body_head-1){
                break;
            }
            i++;
            j++;
            if (i == body.length) {
                i = 0;
            }
        }
        body = body2;
        body_tail = 0;
        body_head = j+1;
    }

    public void draw(Graphics g){

        Image im;
        cell c;
        int i = body_head;

        c = body[i];
        switch (c.dir_in){
            case RIGHT -> im = image_head_r;
            case LEFT -> im = image_head_l;
            case UP -> im = image_head_u;
            default -> im = image_head_d;
        }

        g.drawImage(im, c.x * mainWindow.CELL_SIZE, c.y * mainWindow.CELL_SIZE, null);

        do {
            if (i == 0) {
                i = body.length;
            }
            i--;
            c = body[i];
            if (c.z == cells_vals.FEED) {
                im = image_feed;
            } else {
                im = image_body;
            }
            g.drawImage(im, c.x * mainWindow.CELL_SIZE, c.y * mainWindow.CELL_SIZE, null);
        } while (i != body_tail);
    }

}
