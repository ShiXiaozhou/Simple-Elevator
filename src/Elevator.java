import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Elevator {
    final static int OPEN_CLOSE_INTERVAL = 1;
    private int currentFloor;
    private double finishTime;
    private MoveCondition moveDirection;
    private DecimalFormat decimalFormat;
    private Request request;
    static List<String> errorInformation = new ArrayList<>();

    public Elevator() {
        request = null;
        decimalFormat = new DecimalFormat("0.0");
    }

    public void setRequest(Request request) {
        if (this.request != null) {
            currentFloor = this.request.getTargetFloor();
        }
        this.request = request;
    }

    public void setMoveDirection() {
        moveDirection = request.getRequestTime() > currentFloor ? MoveCondition.UP :
                (request.getRequestTime() == currentFloor ? MoveCondition.STILL : MoveCondition.DOWN);
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public void outPut() {
        if (this.request != null) {
            System.out.println("(" + request.getTargetFloor() + "," + moveDirection + "," +
                    decimalFormat.format(finishTime) + ")");
        }
    }

    public Request getRequest() {
        return request;
    }

    static void printErrorInformation() {
        for (String error : errorInformation) {
            System.out.println(error);
        }
    }

}
