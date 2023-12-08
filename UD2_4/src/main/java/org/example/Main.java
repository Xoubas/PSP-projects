package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Greeting g = new Greeting();
        Thread[] studentThreads = new Thread[21];
        int teacher = new Random().nextInt(20);

        for (int i = 0; i < studentThreads.length; i++) {
            if (teacher == i) {
                studentThreads[i] = new Thread(new Teacher("PepeCalo", g));
            } else {
                studentThreads[i] = new Thread(new Student("Jorgito" + i, g));
            }
            studentThreads[i].start();
        }
    }
}