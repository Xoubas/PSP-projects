package org.example;

public class Depositor implements Runnable {
    private Mailbox mailbox;

    public Depositor(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    @Override
    public void run() {
        while (true) {
            Message mes = new Message("Hello");
            System.out.println("deposit");
            mailbox.deposit(mes);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}