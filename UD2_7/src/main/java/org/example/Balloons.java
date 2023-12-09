package org.example;

import java.util.ArrayList;

public class Balloons {
    private ArrayList<Balloon> balloons;
    private final long maxInflatingBalloons;
    private long inflatingBalloons = 0;

    public Balloons(int maxBalloons, int maxInflatingBalloons) {
        balloons = new ArrayList<Balloon>(maxBalloons);
        this.maxInflatingBalloons = maxInflatingBalloons;
    }

    public synchronized void inflate(Balloon balloon, String inflater) {
        while (!balloon.isExploded()) {
            balloon.inflate();
            System.out.println(inflater + ": inflating balloon" + balloon.getId());
            try {
                wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        inflatingBalloons--;
    }

    public void prick() {

    }

    public synchronized Balloon grabBalloon() {
        Balloon balloon = null;
        while (inflatingBalloons >= maxInflatingBalloons) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (Balloon b : balloons) {
            if (!b.isInflating() && !b.isExploded()) {
                b.setInflating(true);
                inflatingBalloons++;
                balloon = b;
            }
        }
        return balloon;
    }

    public boolean isEmpty() {
        for (Balloon balloon : balloons) {
            if (!balloon.isExploded())
                return false;
        }
        return true;
    }
}
