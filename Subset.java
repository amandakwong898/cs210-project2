package edu.umb.cs210.p2;

import stdlib.In;
import stdlib.StdOut;

public class Subset {
    protected static ResizingArrayRandomQueue<String> subset(String[] args) {
        ResizingArrayRandomQueue q = new ResizingArrayRandomQueue(); // create an object q
        In in = new In(args[1]);
        while (!in.isEmpty()) {
            q.enqueue(in.readString()); // read strings from file (args[1]) and enqueue them into q
        }
        return q; // return q
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        ResizingArrayRandomQueue<String> q = subset(args);
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}
