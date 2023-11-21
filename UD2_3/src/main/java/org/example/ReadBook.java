package org.example;

import java.util.Random;

public class ReadBook {
    public static void read(Book b1, Book b2, Student student) {
        Book bA, bB;
        if (b1.getId() < b2.getId()) {
            bA = b1;
            bB = b2;
        } else {
            bA = b2;
            bB = b1;
        }
        synchronized (bA) {
            synchronized (bB) {
                int sleepTime = new Random().nextInt(10000) + 5000;
                System.out.println(student.getName() + " will read for " + 5 + " minutes");
                try {
                    Thread.currentThread().sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
