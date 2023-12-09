package org.example;

public class Balloon {
    private int id;
    private boolean exploded = false;
    private long volume = 0;
    private boolean isInflating = false;

    public void inflate() {
        if (volume < 5) {
            volume++;
        } else {
            explode();
        }
    }

    public void explode() {
        exploded = true;
    }

    public int getId() {
        return id;
    }

    public boolean isExploded() {
        return exploded;
    }

    public long getVolume() {
        return volume;
    }

    public boolean isInflating() {
        return isInflating;
    }

    public void setInflating(boolean inflating) {
        isInflating = inflating;
    }
}
