import java.awt.*;

public class cell {

    private final int x, y;
    private vals v = vals.EMPTY;

    cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public vals getV() {
        return v;
    }

    public void setV(vals v) {
        this.v = v;
    }

    public void draw(Graphics g, Image image) {
        g.drawImage(image, x * mainWindow.CELL_SIZE, y * mainWindow.CELL_SIZE, null);
    }

}
