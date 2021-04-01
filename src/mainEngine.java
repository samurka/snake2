import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainEngine extends JPanel {

    cell[][] f;
    snake s;
    apple a1, a2;
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
                if(key == KeyEvent.VK_LEFT && s.d != dirs.RIGHT){
                    s.d = dirs.LEFT;
                } else if (key == KeyEvent.VK_RIGHT && s.d != dirs.LEFT){
                    s.d = dirs.RIGHT;
                } else if (key == KeyEvent.VK_UP && s.d != dirs.DOWN){
                    s.d = dirs.UP;
                } else if (key == KeyEvent.VK_DOWN && s.d != dirs.UP) {
                    s.d = dirs.DOWN;
                }
            }
        });

        setFocusable(true);

        setBackground(Color.BLACK);

        draw();

    }

    cell[][] emptyField(){
        f = new cell[mainWindow.FIELD_SIZE][mainWindow.FIELD_SIZE];
        for (int i = 0; i < mainWindow.FIELD_SIZE; i++){
            for(int j = 0; j < mainWindow.FIELD_SIZE; j++){
                f[i][j] = new cell(i,j,vals.EMPTY);
            }
        }
        return f;
    }

    void gameOver(){
        s.timer.stop();
        a1.timer.stop();
        a2.timer.stop();

        gameOver = true;
        repaint();

    }

    void findAndRelocateApple(cell c){

        if(a1.c.equals(c)){
            a1.locateApple();
        } else {
            a2.locateApple();
        }

    }

    void draw(){
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

    }

}
