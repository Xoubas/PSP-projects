package org.example;

public class Balloon {
    private static int count = 0;
    private String id;
    private boolean exploded = false;
    private int volume = 0;
    private boolean isInflating = false;

    public Balloon() {
        count++;
        id = "BL" + count;
    }

    public void inflate() {
        if (volume < 4) {
            volume++;
        } else {
            volume++;
            explode();
        }
    }

    public void explode() {
        exploded = true;
    }

    public String getId() {
        return id;
    }

    public boolean isExploded() {
        return exploded;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isInflating() {
        return isInflating;
    }

    public void setInflating(boolean inflating) {
        isInflating = inflating;
    }
}
