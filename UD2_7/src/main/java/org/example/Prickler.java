package org.example;

public class Prickler implements Runnable {
    private static int count = 0;
    private String id;
    private Balloons balloons;

    public Prickler(Balloons balloons) {
        count++;
        id = "PR" + count;
        this.balloons = balloons;
    }

    @Override
    public void run() {
        while (!balloons.isEmpty()) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            balloons.prick(id);
        }
    }
}
