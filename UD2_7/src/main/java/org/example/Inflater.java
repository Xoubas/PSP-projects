package org.example;

import java.util.ArrayList;

public class Inflater implements Runnable {
    private static int count = 0;
    private String id;
    private Balloons balloons;

    public Inflater(Balloons balloons) {
        count++;
        id = "IN" + count;
        this.balloons = balloons;
    }

    @Override
    public void run() {
        Balloon balloon = balloons.grabBalloon();
        if (balloon != null) {
            balloons.inflate(balloon, id);
        }
    }
}
