package org.example;

public class InfoThread implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] " + "Inside the thread");
        System.out.println("[" + threadName + "] " + "Priority: " +
                Thread.currentThread().getPriority());
        Thread.yield();
        System.out.println("[" + threadName + "] " + "Id: " +
                Thread.currentThread().getId());
        System.out.println("[" + threadName + "] " + "ThreadGroup: " +
                Thread.currentThread().getThreadGroup().getName());
        System.out.println("[" + threadName + "] " + "ThreadGroup count: " +
                Thread.currentThread().getThreadGroup().activeCount());
    }
}