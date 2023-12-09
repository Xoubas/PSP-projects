package org.example;

public class Main {
    public static void main(String[] args) {
        int maxBalloons = 10;
        int maxPricklers = 5;
        int maxInflaters = 5;
        int maxInflatingBalloons = 3;

        Balloons balloons = new Balloons(maxBalloons, maxInflatingBalloons);

        for (int i = 2; i < maxInflaters; i++) {
            new Thread(new Inflater(balloons)).start();
//            new Thread(new Prickler(balloons)).start();
        }
    }
}
