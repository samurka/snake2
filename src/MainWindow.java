import javax.swing.*;

public class MainWindow {

    public final static int CELL_SIZE = 16;
    public final static int FIELD_SIZE = 20;

    MainWindow(){

        JFrame jfrm = new JFrame("Змейка");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(CELL_SIZE * FIELD_SIZE, CELL_SIZE * FIELD_SIZE);
        jfrm.setLocation(400, 400);

        jfrm.add(new MainEngine());

        jfrm.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
