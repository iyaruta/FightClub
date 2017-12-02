package home.inna.fc.battle;

public enum Zone {

    HEAD(1), CHEST(2), STOMACH(3), BELT(4), LEGS(5);

    private final int num;

    Zone(int num) {
        this.num = num;
    }

    public boolean hasBlocked(Zone attack, int blocks) {
        int attackNum = attack.num;
        if (attackNum < this.num) {
            attackNum += 5;
        }

        int zoneBlocks = this.num + blocks - 1;
        return zoneBlocks >= attackNum;
    }

}
