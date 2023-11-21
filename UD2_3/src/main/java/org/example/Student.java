package org.example;

import java.util.Random;

public class Student implements Runnable {
    private String name;
    private static int id = 43;
    private Book[] books;
    public Student(String name, Book[] books) {
        this.name = name;
        this.books = books;
        id++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        int b1, b2;
        while (true) {
            Random ran1 = new Random();
            b1 = ran1.nextInt(books.length);
            System.out.println(name + " picked book " + b1);

            do {
                Random ran2 = new Random();
                b2 = ran2.nextInt(books.length);
                if (b1 != b2)
                    System.out.println(name + " picked book " + b2);
            } while (b1 == b2);

            ReadBook.read(books[b1], books[b2], this);

            long tsleep = new Random().nextLong(10000) + 5000;
            try {
                Thread.currentThread().sleep(tsleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
