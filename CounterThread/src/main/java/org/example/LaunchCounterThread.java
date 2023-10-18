package org.example;

public class LaunchCounterThread {
    private static final int NUM_THREADS = 10;

    public static void main(String[] args) throws InterruptedException {
        //Initialize the counter object
        Counter counter = new Counter();
        // Create ten threads
        Thread[] th = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            CounterThread ct = new CounterThread(counter);
            th[i] = new Thread(ct, "CounterThread" + i);
            th[i].start();
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            th[i].join();
        }
        System.out.println("The final value of counter is " + counter.getCount());
    }
}
