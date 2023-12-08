package org.example;

import java.util.Arrays;

/*
There are four students sharing nine books. The books can be stored in an array.
 The students select two books at random and, once obtained, use them for a random
 time of between one and three hours. After this time, they return them, so that
 they can be used by other students, and rest for a random time of between one and
 two hours. They then select another two books, and the cycle continues. The Book class
 may not have any functionality beyond having an identifier that can be used in messages
 referencing a book. The simulation does not have to be in real time.
 */
public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[9];

        for (int i = 0; i < 9; i++)
            books[i] = new Book("Book" + (i + 1), i + 1);

        Student s1 = new Student("John", books);
        Student s2 = new Student("Sarah", books);
        Student s3 = new Student("Gonzalo", books);

        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        Thread t3 = new Thread(s3);

        t1.start();
        t2.start();
        t3.start();
    }
}