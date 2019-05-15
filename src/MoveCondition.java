public enum MoveCondition {
    STILL(0), UP(1), DOWN(2), ERROR(3);
    public final int index;

    private MoveCondition(int index) {
        this.index = index;
    }
}
