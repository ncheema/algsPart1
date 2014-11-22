package com.ncheema;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by navjotcheema on 11/16/14.
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        public Node(Item item) {
            this.item = item;
        }
        Node next;
        Node prev;
        Item item;
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
        size++;
    }
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!!!");
        Item item = first.item;
        if (first == last)  last = null;
        first = first.next;
        if (first !=null ) first.prev = null;
        size--;
        return item;
    }
    // delete and return the item at the end
    public Item removeLast()   {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!!!");
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
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
            return current != null ;
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
        Integer i = new Integer(3);
        deque.addFirst(i);
        deque.addFirst(1);
        StdOut.println("Size is: " + deque.size());
        StdOut.println("Removed: " + deque.removeFirst());
        StdOut.println("Size is: " + deque.size());
        StdOut.println("Removed: " + deque.removeFirst());
        StdOut.println("Size is: " + deque.size());

        StdOut.println("\nTesting adding & removing Last... ");
        deque.addLast(2);
        deque.addLast(7);

        StdOut.println("Removing element: "+deque.removeLast());
        for (Integer temp: deque) {
            StdOut.println(temp);
        }

    }
}