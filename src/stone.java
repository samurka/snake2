import javax.swing.*;
import java.awt.*;

public class stone {

    private final static Image image;
    private final cell c;

    static {
        ImageIcon iid = new ImageIcon("stone.png");
        image = iid.getImage();
    }

    stone(mainEngine me) {
        c = me.getRandomEmptyCell();
        c.setV(vals.STONE);
    }

    public void draw(Graphics g) {
        c.draw(g, image);
    }

}
