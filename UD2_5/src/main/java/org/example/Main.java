package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Mailbox mailbox = new Mailbox();
        Collector c = new Collector(mailbox);
        Depositor d = new Depositor(mailbox);

        Thread col = new Thread(c);
        Thread dep = new Thread(d);

        dep.start();
        col.start();
    }
}
