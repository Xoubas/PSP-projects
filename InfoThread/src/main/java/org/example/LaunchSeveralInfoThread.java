package org.example;

public class LaunchSeveralInfoThread {
    public static void main(String[] args) {
        // main thread
        Thread.currentThread().setName("Main");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().toString());
        ThreadGroup even = new ThreadGroup("Even threads");
        ThreadGroup odd = new ThreadGroup("Odd threads");
        Thread localThread = null;
        for (int i = 0; i < 10; i++) {
            localThread = new Thread((i % 2 == 0) ? even : odd, new InfoThread(), "Thread" + i);
            localThread.setPriority(i + 1);
            localThread.start();
        }
        try {
            localThread.join(); // --> Will wait until last thread ends
            // like a waitFor() for processes
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.err.println("The main thread was interrupted while waiting for "
                    + localThread.toString() + "to finish");
        }
        System.out.println("Main thread ending");
    }
}

