package org.example;

public class Mailbox {
    private Message message;

    public synchronized void setMessage(Message m) {
        this.message = m;
    }

    public synchronized void deposit(Message m) {
        while (this.message != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Deposited");
        setMessage(m);
        notifyAll();
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
        notifyAll();
        return mesOut;
    }
}
