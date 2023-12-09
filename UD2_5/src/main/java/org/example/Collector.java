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
            try {
                Thread.sleep(new Random().nextLong(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Message mes = mailbox.collect();
            System.out.println(mes.getMessage());
        }
    }
}
