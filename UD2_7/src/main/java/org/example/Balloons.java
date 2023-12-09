package org.example;

import java.util.ArrayList;

public class Balloons {
    private ArrayList<Balloon> balloons;
    private final long maxInflatingBalloons;
    private final long maxBalloons;
    private long inflatingBalloons = 0;

    public Balloons(int maxBalloons, int maxInflatingBalloons) {
        this.maxBalloons = maxBalloons;
        this.maxInflatingBalloons = maxInflatingBalloons;
        balloons = new ArrayList<Balloon>(maxBalloons);
        for (int i = 0; i < maxBalloons; i++) {
            balloons.add(new Balloon());
        }
    }

    public synchronized void inflate(Balloon balloon, String inflater) {
        while (!balloon.isExploded()) {
            balloon.inflate();
            System.out.println(inflater + " inflating " + balloon.getId() +" - "+ balloon.getVolume() + "L");
            try {
                wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        balloon.explode();
        System.out.println(balloon.getId() + " has exploded");
        inflatingBalloons--;
    }

    public synchronized void prick(String prickler) {
        for (Balloon balloon : balloons) {
            if (!balloon.isInflating() && !balloon.isExploded()) {
                balloon.explode();
                inflatingBalloons--;
                System.out.println(prickler + " has prickled balloon " + balloon.getId());
                notifyAll();
                break;
            }
        }
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
                break;
            }
        }
        return balloon;
    }

    public synchronized boolean isEmpty() {
        for (Balloon balloon : balloons) {
            if (!balloon.isExploded())
                return false;
        }
        return true;
    }
}
