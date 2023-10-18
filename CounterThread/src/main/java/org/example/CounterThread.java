package org.example;

public class CounterThread implements Runnable {
    private Counter counter;
    private static final int NUM_OPS = 300;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_OPS; i++) {
            try {
                counter.increase();
                System.out.println(Thread.currentThread().getName() + " " +
                        counter.getCount());
                Thread.sleep((long) (Math.random() * 100 + 50));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
