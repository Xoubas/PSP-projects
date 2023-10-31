package org.example;

import java.util.Objects;

public class Book {
    private String title;
    private int id;

    public Book(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return title;
    }
}
