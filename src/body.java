public class body {

    private cell[] b;
    private int head, tail;

    body(cell[] cc) {
        int l = cc.length;
        b = new cell[l * 3];
        for (int i = 0; i < l; i++) {
            b[i] = cc[i];
        }
        tail = 0;
        head = l - 1;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public cell getCell(int i) {
        return b[i];
    }

    public int getLength() {
        return b.length;
    }

    public void moveHead(cell c) {
        head++;
        if (head == tail || tail == 0 && head == b.length) growBody();
        if (head == b.length) head = 0;
        b[head] = c;
    }

    private void growBody() {
        // head сюда прилетает уже инкрементированный
        // соответственно и улететь должен тоже инкрементированный
        cell[] b2 = new cell[b.length * 2];
        int i = tail;
        int j = 0;
        while (true) {
            b2[j] = b[i];
            i++;
            j++;
            if (i == head) break;
            if (i == b.length) i = 0;
        }
        b = b2;
        tail = 0;
        head = j;
    }

    public void moveTail() {
        tail++;
        if (tail == b.length) tail = 0;
    }
}
