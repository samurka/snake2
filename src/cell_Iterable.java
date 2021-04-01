import java.util.Iterator;

public class cell_Iterable implements Iterable<cell> {

    body iterObj;

    cell_Iterable(body b) {
        iterObj = b;
    }

    @Override
    public Iterator<cell> iterator() {
        return new cell_Iterator(iterObj);
    }
}
