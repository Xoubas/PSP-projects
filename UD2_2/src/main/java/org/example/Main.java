package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int num = new Random().nextInt(100);
        HiddenNumber hiddenNumber = new HiddenNumber(num);

        Thread[] thA = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Threads thCheck = new Threads(hiddenNumber);
            thA[i] = new Thread(thCheck, "Thread" + i);
            thA[i].start();
        }
    }
}