import java.util.Iterator;

public class cell_Iterator implements Iterator<cell> {

    private body iterObj;
    private int curInd;
    private boolean hasNext = true;

    cell_Iterator(body b) {
        iterObj = b;
        curInd = iterObj.getHead();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public cell next() {
        cell c = iterObj.getCell(curInd);
        if (curInd == iterObj.getTail()) {
            hasNext = false;
        } else {
            if (curInd == 0) {
                curInd = iterObj.getLength();
            }
            curInd--;
        }
        return c;
    }
}


