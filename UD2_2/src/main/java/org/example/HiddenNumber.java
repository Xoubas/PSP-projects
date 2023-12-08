package org.example;

public class HiddenNumber {
    private final int number;
    private boolean end = false;

    public HiddenNumber(int number) {
        this.number = number;
    }

    public boolean getEnd() {
        return end;
    }

    public synchronized void setEnd(boolean end) {
        this.end = end;
    }

    synchronized public int numberGuess(int num) {
        if (num != number && !end) {
            return -1;
        } else if (num == number && !end) {
            setEnd(true);
            return 1;
        } else {
            return 0;
        }
    }
}
