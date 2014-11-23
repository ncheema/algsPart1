import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by navjotcheema on 11/16/14.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Node next;
        private Node prev;
        private Item item;
        public Node(Item item) {
            this.item = item;
        }

    }


    private int size = 0;
    private Node first = null;
    private Node last = null;

    // construct an empty deque
    public Deque() {
        /*first = new Node(null);
        last = new Node(null);
        first.next = last;
        first.prev = first;
        last.prev = first;
        */
        first = null;
        last = null;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return  return the number of items on the deque
     */
    public int size()   {
        return size;
    }
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException("Item is null");
        Node temp = first;
        first = new Node(item);
        first.next = temp;
        first.prev = null;
        if (temp != null)   temp.prev = first;
        if (last == null)   last = first;
        size++;
    }
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node(item);
        last.prev = oldLast;
        last.next = null;
        if (oldLast != null) oldLast.next = last;
        if (first == null) first = last;
        size++;
    }
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!!!");
        Item item = first.item;
        if (first == last)  last = null;
        first = first.next;
        if (first != null) first.prev = null;
        size--;
        return item;
    }
    // delete and return the item at the end
    public Item removeLast()   {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!!!");
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        if (last == null) first = last;
        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        if (deque.isEmpty()) {
            StdOut.println("Passed");
        }
        StdOut.println("Testing adding & removing First... ");

  /*      deque.addFirst(5);
        deque.addFirst(1);
        StdOut.println("Size is: " + deque.size());
        StdOut.println("Removed: " + deque.removeFirst());
        StdOut.println("Size is: " + deque.size());
        StdOut.println("Removed: " + deque.removeFirst());
        StdOut.println("Size is: " + deque.size());

       StdOut.println("\nTesting adding & removing Last... ");
     */
        deque.addFirst(1);
        deque.addLast(7);
     //   StdOut.println("Removed: " + deque.removeFirst());
     //   StdOut.println("Removed: " + deque.removeLast());
      //  deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(7);
        deque.addFirst(8);
        deque.addLast(9);
        deque.addLast(18);

    //    StdOut.println("Removed: " + deque.removeFirst());

        StdOut.println("Size is: " + deque.size());
       // StdOut.println("Removing element: "+deque.removeLast());
        for (int temp: deque) {
            StdOut.println(temp);
        }

        StdOut.println("Removed: " + deque.removeLast());

        StdOut.println("Removed: " + deque.removeLast());
        StdOut.println("Removed: " + deque.removeFirst());
        StdOut.println("Removed: " + deque.removeLast());
        StdOut.println("Removed: " + deque.removeFirst());
        StdOut.println("Size is: " + deque.size());
        // StdOut.println("Removing element: "+deque.removeLast());
        for (int temp: deque) {
            StdOut.println(temp);
        }
    }
}