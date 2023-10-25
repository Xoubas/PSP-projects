package org.example;

import java.util.Random;

public class Student implements Runnable {
    private String name;
    private static int id = 43;

    public Student(String name) {
        this.name = name;
        id++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {

    }
}
