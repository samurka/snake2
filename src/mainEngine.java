import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class mainEngine extends JPanel {

    private cell[][] f;
    private final snake s;
    private apple[] aa;
    private stone[] ss;
    private boolean gameOver;
    private int q;

    mainEngine() {
        createField();
        s = new snake(this);
        createApples();
        createStones();
        gameOver = false;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                s.changeDir(e.getKeyCode());
            }
        });

        setFocusable(true);
        setBackground(Color.BLACK);
        draw();
    }

    private void createField() {
        f = new cell[mainWindow.FIELD_SIZE][mainWindow.FIELD_SIZE];
        for (int i = 0; i < mainWindow.FIELD_SIZE; i++) {
            for (int j = 0; j < mainWindow.FIELD_SIZE; j++) {
                f[i][j] = new cell(i, j);
            }
        }
    }

    private void createApples() {
        aa = new apple[mainWindow.QUANTITY_APPLES];
        for (int i = 0; i < mainWindow.QUANTITY_APPLES; i++) {
            aa[i] = new apple(this);
        }
    }

    private void createStones() {
        ss = new stone[mainWindow.QUANTITY_STONES];
        for (int i = 0; i < mainWindow.QUANTITY_STONES; i++) {
            ss[i] = new stone(this);
        }
    }

    public void gameOver() {
        s.stopTimer();
        for (apple i : aa) {
            i.stopTimer();
        }
        gameOver = true;
        repaint();
    }

    public cell[] getInitialSnakeCells() {
        cell[] cc = new cell[mainWindow.INITIAL_QUANTITY_SNAKE];
        for (int i = 0; i < mainWindow.INITIAL_QUANTITY_SNAKE; i++) {
            cc[i] = f[i][mainWindow.FIELD_SIZE / 2];
        }
        return cc;
    }

    public cell getRandomEmptyCell() {
        Random r = new Random();
        cell c;
        do {
            c = f[r.nextInt(mainWindow.FIELD_SIZE)][r.nextInt(mainWindow.FIELD_SIZE)];
        } while (c.getV() != vals.EMPTY);
        return c;
    }

    public void findAndRelocateApple(cell c) {
        q++;
        for (apple i : aa) {
            boolean done = i.relocateIfEqual(c);
            if (done) break;
        }
    }

    public cell tryMoveHead(cell c, dirs d) {

        int x = c.getX();
        int y = c.getY();

        switch (d) {
            case RIGHT -> x++;
            case LEFT -> x--;
            case UP -> y--;
            case DOWN -> y++;
        }

        if (x < 0 || y < 0 || x == mainWindow.FIELD_SIZE || y == mainWindow.FIELD_SIZE) {
            gameOver();
            return null;
        }

        if (f[x][y].getV() == vals.SNAKE || f[x][y].getV() == vals.FEED || f[x][y].getV() == vals.STONE) {
            gameOver();
            return null;
        }

        return f[x][y];

    }

    public void draw() {
        repaint();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        s.draw(g);
        for (apple i : aa) {
            i.draw(g);
        }
        for (stone i : ss) {
            i.draw(g);
        }
        Font f = new Font("Arial", Font.BOLD, 14);
        g.setFont(f);
        g.drawString("" + q, mainWindow.CELL_SIZE * mainWindow.FIELD_SIZE - 25, 20);
        if (gameOver) {
            g.setColor(Color.white);
            f = new Font("Arial", Font.BOLD, 36);
            g.setFont(f);
            g.drawString("Game Over", mainWindow.CELL_SIZE * mainWindow.FIELD_SIZE / 2 - 100, mainWindow.CELL_SIZE * mainWindow.FIELD_SIZE / 2);
        }
    }

}
