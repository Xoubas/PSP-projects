package org.example;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Greeting {
    private boolean teacherArrived = false;

    public synchronized void teacherEnters(String name) {
        System.out.println(name + ": Good morning class!!!");
        teacherArrived = true;
        notifyAll();
    }

    public synchronized void studentEnters(String name) {
        if (teacherArrived) {
            System.out.println(name + "Perdon");
        } else {
            while (!teacherArrived) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(name + ": Ohayou sensei");
        }
    }
}
