package org.example;

import java.util.Random;

public class RandomThread implements Runnable {
    /*
    Create a Java class that implements the Runnable interface.

    The run() method of the class must do the following:

    Display a welcome message with the name of the current thread.
    Repeat five times:
    Get a random number between 10 and 500 (use java.util.Random).
    Pause the execution of the current thread for the number of miliseconds equal to the
    random number obtained above.
    Display a goodbye message with the name of the current thread.
    Create a Java executable class to launch two threads created using the previous class. Thie main
    thread waits for the other two threads to finish and then displays a message indicating
    that it has finished.
     */
    @Override
    public void run() {
            System.out.println("Welcome thread: " + Thread.currentThread().getName());
            Random rand = new Random();
            long ms = rand.nextLong(500) + 10;
            try {
                for(int i = 0; i < 5; i++) {
                    Thread.sleep(ms);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        System.out.println("Goodbye thread: " + Thread.currentThread().getName());
    }
}