public class Floor {
    final static int MAX_FLOOR = 10;
    final static int MIN_FLOOR = 1;
    final static double FLOOR_INTERVAL = 0.5;

    private int floorIndex;
    private MoveCondition moveCondition;

    public Floor(int floorIndex, MoveCondition moveCondition) {
        this.floorIndex = floorIndex;
        this.moveCondition = moveCondition;
    }

    public MoveCondition getMoveCondition() {
        return moveCondition;
    }

    public void setMoveCondition(MoveCondition moveCondition) {
        this.moveCondition = moveCondition;
    }

    public int getFloorIndex() {
        return floorIndex;
    }

    public void setFloorIndex(int floorIndex) {
        this.floorIndex = floorIndex;
    }
}
