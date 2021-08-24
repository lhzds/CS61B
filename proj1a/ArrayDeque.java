/** Deque implemented by using array with circular topology. */
public class ArrayDeque<T> {
    /** Creates an empty array deque. */
    public ArrayDeque() {
        left = 0;
        right = 0;
        items = (T[]) new Object[INITLENGTH];
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return numOfItemBetween(left, right);
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (needExpand()) {
            expand();
        }

        left = minusOne(left);
        items[left] = item;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (needExpand()) {
            expand();
        }

        items[right] = item;
        right = addOne(right);
    }

    /** Prints the items in the deque from first to last,
     *  separated by a space. */
    public void printDeque() {
        if (isEmpty()) {
            return;
        }

        int pos = left;

        while (pos != minusOne(right)) {
            System.out.print(items[pos] + " ");
            pos = addOne(pos);
        }
        System.out.println(items[pos]);
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T old = items[left];
        items[left] = null;
        left = addOne(left);

        if (needShrink()) {
            shrink();
        }
        return old;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        right = minusOne(right);
        T old = items[right];
        items[right] = null;

        if (needShrink()) {
            shrink();
        }
        return old;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists,
     *  returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        return getNthItem(index);
    }

    private static int moduloAdd(int A, int B, int M) {
        int tmp = (A + B) % M;
        if (tmp > 0) {
            return tmp;
        } else {
            return (tmp + M) % M;
        }
    }

    private int minusOne(int pos) {
        return moduloAdd(pos, -1, items.length);
    }

    private int addOne(int pos) {
        return moduloAdd(pos, 1, items.length);
    }

    private int numOfItemBetween(int leftEnd, int rightEnd) {
        return moduloAdd(rightEnd, -leftEnd, items.length);
    }

    private T getNthItem(int N) {
        return items[moduloAdd(left, N, items.length)];
    }

    private boolean needExpand() {
        return items.length - 1 == size();
    }

    private boolean needShrink() {
        double usageFactor = (double)size() / items.length;
        return usageFactor < SMALLESTUSAGEFACTOR;
    }

    private static void circularArrayCopy(Object[] src, int srcPos,
                                          Object[] dst, int dstPos, int length) {
        for (int i = 0; i < length; ++i) {
            dst[dstPos] = src[srcPos];
            srcPos = moduloAdd(srcPos, 1, src.length);
            dstPos = moduloAdd(dstPos, 1, dst.length);
        }
    }

    private void expand() {
        T[] newItems = (T[]) new Object[items.length * 2];
        int size = size();
        circularArrayCopy(items, left, newItems, 0, size);
        left = 0;
        /* Should not call directly size() which depends on the value of left and right */
        right = size;
        items = newItems;
    }

    private void shrink() {
        T[] newItems = (T[]) new Object[items.length / 2];
        int size = size();
        circularArrayCopy(items, left, newItems, 0, size());
        left = 0;
        /* Should not directly call size() which depends on the value of left and right */
        right = size;
        items = newItems;
    }

    private static final int INITLENGTH = 1;
    private static final double SMALLESTUSAGEFACTOR = 0.25;

    private T[] items;
    private int left;  // position of the leftmost item
    private int right; // the position to the right of the rightmost item
}

