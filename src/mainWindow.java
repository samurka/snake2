import javax.swing.*;

public class mainWindow {

    protected final static int CELL_SIZE = 16;
    protected final static int FIELD_SIZE = 21;
    protected final static int ONE_TICK = 100;
    protected final static int QUANTITY_APPLES = 3;
    protected final static int QUANTITY_STONES = 6;
    protected final static int INITIAL_QUANTITY_SNAKE = 3;

    mainWindow() {

        JFrame jfrm = new JFrame("Змейка");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(CELL_SIZE * FIELD_SIZE + 16, CELL_SIZE * FIELD_SIZE + 40);
        jfrm.setLocation(400, 400);

        jfrm.add(new mainEngine());

        jfrm.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(mainWindow::new);
    }
}
