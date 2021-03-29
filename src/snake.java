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
    static Image image;

    static {
        ImageIcon iid = new ImageIcon("snake.png");
        image = iid.getImage();
    }

    snake(mainEngine me){

        mainEngine = me;
        cur_dir = dir_vals.RIGHT;
        timer = new Timer(mainWindow.ONE_TICK,this);

        body = new cell[10];
        body_head = 2;
        body_tail = 0;
        for(int i = 0; i <= body_head; i++){
            cell c = mainEngine.f[i][mainWindow.FIELD_SIZE / 2];
            c.z = cells_vals.SNAKE;
            body[i] = c;
        }

        timer.start();

    }

    public void actionPerformed(ActionEvent ae){

        int x = body[body_head].x;
        int y = body[body_head].y;

        switch (cur_dir){
            case RIGHT:
                x++;
                break;
            case LEFT:
                x--;
                break;
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
        };

        if(x < 0 || y < 0 || x == mainWindow.FIELD_SIZE || y == mainWindow.FIELD_SIZE){
            mainEngine.gameOver();
            return;
        };

        if(mainEngine.f[x][y].z == cells_vals.SNAKE || mainEngine.f[x][y].z == cells_vals.FEED){
            mainEngine.gameOver();
            return;
        };

        body_head++;

        if(body_head == body_tail || body_tail == 0 && body_head == body.length){
            // массив мал, надо увеличить
            cell[] body2 = new cell[body.length*2];
            for(int i = 0; i < body.length; i++){
                body2[i] = body[i];
            }
            body = body2;
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

        c = body[body_tail];
        if(c.z == cells_vals.FEED) {
            c.z = cells_vals.SNAKE;
        } else {
            c.z = cells_vals.EMPTY;
            body_tail++;
            if(body_tail == body.length){
                body_tail=0;
            };
        };

        mainEngine.draw();

    }

    public void draw(Graphics g){

        int i = body_head;
        while(true){
            g.drawImage(image, body[i].x * mainWindow.CELL_SIZE, body[i].y * mainWindow.CELL_SIZE, null);
            if(i == body_tail){
                break;
            }
            if(i == 0) {
                i = body.length;
            };
            i--;
        }
    }

}
