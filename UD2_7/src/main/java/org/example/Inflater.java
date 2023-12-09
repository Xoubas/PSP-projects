package org.example;

import java.util.ArrayList;

public class Inflater implements Runnable {
    private static long count = 1;
    private String id;
    private ArrayList<Balloon> balloons;

    public Inflater(ArrayList<Balloon> balloons) {
        id = "IN" + count;
        count++;
        this.balloons = balloons;
    }

    @Override
    public void run() {

    }
}
