package com.ncheema;

import java.util.Iterator;

/**
 * Created by navjotcheema on 11/19/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item items[];
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1]; //cast to item type (since java doesn't allow generic Arrays
        size = 0;
    }
    // is the queue empty?
    public boolean isEmpty() {
        return size==0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new java.util.NoSuchElementException("Item is null");
        //if array is full, create new array, twice the size
        if (size == items.length) {
            resize(2*items.length);
        }
        items[size++] = item;
    }

    //helper function to resize the Item array
    public void resize(int newCapacity) {
        Item tempArray[] = (Item[]) new Object[newCapacity];
        for (int i = 0; i < items.length; i++) {
            tempArray[i] = items[i];
        }
        items = tempArray;
    }

    // delete and return a random item
    public Item dequeue()      {
       int index =   StdRandom.uniform(size);
        Item pop = items[index];
        items[index] = null;
        if (index != size-1) {
            items[index] = items[--size];   //change the item at index w/ last item in the array,and update size
        }
        //resize array if its quarter full
        if (size > 0 && size == items.length/4)
            resize(items.length/2);
        return pop;
    }

    // return (but do not delete) a random item
    public Item sample()      {
        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int randIndexArray[];
        private int currentIndex;
        RandomizedQueueIterator() {
            randIndexArray = new int[size];
            currentIndex = 0;
            for (int i = 0; i < randIndexArray.length; i++)
                randIndexArray[i] = i;
            StdRandom.shuffle(randIndexArray);  //shuffle the index values
        }

        @Override
        public boolean hasNext() {
            return currentIndex<randIndexArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("No Elements");

            return items[randIndexArray[currentIndex++]];

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not Supported Opertaion");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randQueue = new RandomizedQueue<Integer>();

        for (int i = 1; i <= 10; i++){
            randQueue.enqueue(i);
        }
        StdOut.println("Size is: :" + randQueue.size());
        StdOut.println("Sample: "+randQueue.sample());
        StdOut.println("Dequeue: "+randQueue.dequeue());
        StdOut.println("Size is: " + randQueue.size());

        StdOut.println("Testing Iterator... ");
        Iterator<Integer> it =  randQueue.iterator();
        Iterator<Integer> it2 =  randQueue.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
        StdOut.println("Testing  ... ");
       for (int i : randQueue) {
           StdOut.println(i);
       }
        StdOut.println("Testing ... ");
        for (int i : randQueue) {
            StdOut.println(i);
        }
    }
}
