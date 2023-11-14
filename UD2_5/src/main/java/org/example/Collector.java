package org.example;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Collector implements Runnable {
    Mailbox mailbox;

    public Collector(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    @Override
    public void run() {
        while (true) {
            Message mes = mailbox.collect();
            System.out.println(mes.getMessage());
            try {
                Thread.sleep(new Random().nextLong(3000) + 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
