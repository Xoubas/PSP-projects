package org.example;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Mailbox mailbox = new Mailbox();
        Collector c = new Collector(mailbox);
        Depositor d = new Depositor(mailbox);

        Thread[] threads1 = new Thread[5];
        Thread[] threads2 = new Thread[5];

        for (int i = 0; i < threads1.length; i++) {
            threads1[i] = new Thread(d);
            threads1[i].start();
        }
        for (int i = 0; i < threads2.length; i++) {
            threads2[i] = new Thread(c);
            threads2[i].start();
        }
    }
}
