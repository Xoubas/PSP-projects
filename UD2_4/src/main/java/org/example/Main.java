package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Greeting g = new Greeting();
        Thread[] threads = new Thread[21];
        int teacher = new Random().nextInt(21);

        for (int i = 0; i < threads.length; i++) {
            if (teacher == i) {
                threads[i] = new Thread(new Teacher("PepeCalo", g));
            } else {
                threads[i] = new Thread(new Student("Jorgito" + i, g));
            }
            threads[i].start();
        }
    }
}