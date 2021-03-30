import javax.swing.*;

public class mainWindow {

    public final static int CELL_SIZE = 16;
    public final static int FIELD_SIZE = 21;
    public final static int ONE_TICK = 100;

    mainWindow(){

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
