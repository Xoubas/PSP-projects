package org.example;

public class RunRandomWaitThreads {
    public static void main(String[] args) {
        Thread t1 = new Thread(new RandomThread());
        Thread t2 = new Thread(new RandomThread());

        t1.start();
        t2.start();

        try {
            new Thread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
