package org.example;

public class Counter {
    private int count = 0;

    public int getCount() {
        return count;
    }

    synchronized public int increase() {
        this.count++;
        return count;
    }
}
