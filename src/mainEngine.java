import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainEngine extends JPanel {

    cell[][] f;
    snake s;
    apple a1, a2;
    apple[] apls;
    boolean gameOver;

    mainEngine(){

        f = emptyField();
        s = new snake(this);
        a1 = new apple(this);
        a2 = new apple(this);
        gameOver = false;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_LEFT && s.cur_dir != dir_vals.RIGHT){
                    s.cur_dir = dir_vals.LEFT;
                } else if (key == KeyEvent.VK_RIGHT && s.cur_dir != dir_vals.LEFT){
                    s.cur_dir = dir_vals.RIGHT;
                } else if (key == KeyEvent.VK_UP && s.cur_dir != dir_vals.DOWN){
                    s.cur_dir = dir_vals.UP;
                } else if (key == KeyEvent.VK_DOWN && s.cur_dir != dir_vals.UP) {
                    s.cur_dir = dir_vals.DOWN;
                }
            }
        });

        setFocusable(true);
//        apls = new apple[mainWindow.FIELD_SIZE];
//        for(int i = 0; i < mainWindow.FIELD_SIZE; i++){
//            apls[i] = new apple(this, i , i);
//        }

        setBackground(Color.BLACK);

        draw();

    }

    public cell[][] emptyField(){
        f = new cell[mainWindow.FIELD_SIZE][mainWindow.FIELD_SIZE];
        for (int i = 0; i < mainWindow.FIELD_SIZE; i++){
            for(int j = 0; j < mainWindow.FIELD_SIZE; j++){
                f[i][j] = new cell(i,j,cells_vals.EMPTY);
            }
        }
        return f;
    }

    public void gameOver(){
        s.timer.stop();
        a1.timer.stop();
        a2.timer.stop();

        gameOver = true;
        repaint();

    }

    public void findAndRelocateApple(cell c){

        if(a1.body.equals(c)){
            a1.locateApple();
        } else {
            a2.locateApple();
        }

    }

    public void draw(){
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        s.draw(g);
        a1.draw(g);
        a2.draw(g);

        if(gameOver){
            g.setColor(Color.white);
            g.drawString("Game Over",10,10);
        }

//        for(apple a : apls){
//           a.draw(g);
//        }
    }

}
