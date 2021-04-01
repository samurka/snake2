import java.util.Iterator;

public class body {

    cell[] b;
    int head, tail;

    body(){
        b = new cell[10];
        head = 0;
        tail = 0;
    }

    cell getHead(){
        return b[head];
    }

    cell getTail(){
        return b[tail];
    }

    void setHead(cell c, int i){
        head = i;
        b[head] = c;
    }

    void moveHead(cell c){
        head++;
        if(head == tail || tail == 0 && head == b.length) growBody();
        if(head == b.length) head = 0;
        b[head] = c;
    }

    void growBody(){
        // head сюда прилетает уже инкрементированный
        // соответственно и улететь должен тоже инкрементированный
        cell[] b2 = new cell[b.length*2];
        int i = tail;
        int j = 0;
        while (true){
            b2[j] = b[i];
            i++;
            j++;
            if(i == head) break;
            if(i == b.length) i = 0;
        }
        b = b2;
        tail = 0;
        head = j;
    }

    void moveTail() {
        tail++;
        if (tail == b.length) tail = 0;
    }
}
