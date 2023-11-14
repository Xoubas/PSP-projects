package org.example;

import java.util.Random;

public class Depositor implements Runnable {
    private Mailbox mailbox;

    public Depositor(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    @Override
    public void run() {
        while (true) {
            Message mes = new Message("Hello");
            mailbox.deposit(mes);
            try {
                Thread.sleep(new Random().nextLong(2000) + 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}