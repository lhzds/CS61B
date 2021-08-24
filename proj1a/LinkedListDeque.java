/** Deque implemented by using doubly linked list with circular sentinel topology. */
public class LinkedListDeque<T> {
    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        ++size;
        ListNode newNode = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        ++size;
        ListNode newNode = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        if (isEmpty()) {
            return;
        }

        ListNode ptr = sentinel.next;

        while (ptr != sentinel.prev) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(ptr.item);
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        --size;
        ListNode removed = sentinel.next;
        removed.next.prev = sentinel;
        sentinel.next = removed.next;
        return removed.item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        --size;
        ListNode removed = sentinel.prev;
        removed.prev.next = sentinel;
        sentinel.prev = removed.prev;
        return removed.item;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. If no such item exists,
     *  returns null. Must not alter the deque! */
    public T get(int index){
        if (index >= size) {
            return null;
        }
        ListNode ptr = sentinel.next;
        for (int i = 0; i < index; ++i) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    /** Same as get, but uses recursion.
     *  Actually call getRecursive(int, int) to do the recursion. */
    public T getRecursive(int index){
        return getRecursive(index, 0, sentinel.next);
    }

    /** Helper function of getRecursive(int) that actually
     *  do the recursion. */
    private T getRecursive(int neededIndex, int curIndex, ListNode node) {
        if (neededIndex >= size) {
            return null;
        }
        if (curIndex == neededIndex) {
            return node.item;
        }
        return getRecursive(neededIndex, curIndex, node.next);
    }

    private class ListNode {
        public ListNode(T item, ListNode prev, ListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public T item;
        public ListNode prev;
        public ListNode next;
    }
    private ListNode sentinel;
    private int size;
}
