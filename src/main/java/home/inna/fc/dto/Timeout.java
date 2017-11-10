package home.inna.fc.dto;

public enum Timeout {

    ONE(60), TWO(120), THREE(180), FOUR(240), FIVE(300);

    private final int value;

    Timeout(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Timeout valueOf(int seconds) {
        int minutes = seconds / 60;
        if (minutes < 0 || minutes > 4) {
            throw new IllegalArgumentException("Timeout not supported");
        }

        return values()[minutes - 1];

    }
}
