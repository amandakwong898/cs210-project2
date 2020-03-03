package edu.umb.cs210.p2;

import stdlib.StdOut;
import stdlib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    private int N; // size of the deque
    private Node first; // head of the deque
    private Node last; // tail of the deque

    // Helper doubly-linked list class.
    protected class Node {
        protected Item item;  // This Node's value
        protected Node next;  // The next Node
        protected Node prev;  // The previous Node

    }

    // Construct an empty deque.
    public LinkedDeque() {
        N = 0;
        first = null;
        last = null;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // The number of items on the deque.
    public int size() {
        return N;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }
        Node f = new Node(); // create a new node
        f.item = item; // item to be added
        if (first != null) {
            f.next = first;
            first.prev = f;
        }
        first = f;
        if (last == null) {
            last = first;
        }
        N += 1;
    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Item is null.");
        }
        Node l = new Node(); // create a new node
        l.item = item; // item to be added
        if (last != null) {
            l.prev = last;
            last.next = l;
        }
        last = l;
        if (first == null) {
            first = last;
        }
        N += 1;
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty.");
        }
        N--;
        Item item = first.item;
        first = first.next;
        return item;
    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from empty.");
        }
        N--;
        Item item = last.item;
        last = last.prev;
        return item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        private Node current; // pointer to current Node in iterator

        // DequeIterator constructor
        DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item; // return the next item
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        if (s.length() < 1) return "[Empty]";
        else return s.toString().substring(0, s.length() - 1);
    }
    
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its " 
            + "several powers, having been originally breathed into a few " 
            + "forms or into one; and that, whilst this planet has gone " 
            + "cycling on according to the fixed law of gravity, from so " 
            + "simple a beginning endless forms most beautiful and most " 
            + "wonderful have been, and are being, evolved. ~ " 
            + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            }
            else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
