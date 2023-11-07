package org.example;

public class Mailbox {
    private Message message;

    private void setMessage(Message m) {
        this.message = m;
    }

    public synchronized void deposit(Message m) {
        while (m != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Deposited");
            setMessage(m);
            notify();
        }
    }

    public synchronized Message collect() {
        while (message == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Collected");
        Message mesOut = message;
        message = null;
        notify();
        return mesOut;
    }
}
