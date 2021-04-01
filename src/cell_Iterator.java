import java.util.Iterator;

public class cell_Iterator implements Iterator<cell> {

    body iterObj;
    int curInd;
    boolean hasNext = true;

    cell_Iterator(body b){
        iterObj = b;
        curInd = b.head;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public cell next() {
        cell c = iterObj.b[curInd];
        if(curInd== iterObj.tail) {
            hasNext = false;
        } else {
            if(curInd==0){
                curInd = iterObj.b.length;
            }
        curInd--;
        }
        return c;
    }
}


