public class Dispatcher {
    private Elevator elevator;
    private RequestQueue requestQueue;
    private double startTime;
    private double finishTime;
    private int startFloor;

    public Dispatcher(RequestQueue requestQueue) {
        startTime = 0.0;
        finishTime = 0.0;
        startFloor = 1;
        elevator = new Elevator();
        this.requestQueue = requestQueue;
    }

    public void runElevator() {
        boolean isFirst = true;
        int timeBefore = 0;
        while(requestQueue.hasNext()) {
            Request request = new Request();
            request.manageRequest(requestQueue.getRequestNext(), timeBefore, true);
            if (!request.isLegalRequest()) {
                continue;
            }
            if (isFirst) {
                if (request.getRequestTime() != 0) {
                    System.out.println("The first time value is not 0, system exit.");
                    System.exit(0);
                } else {
                    isFirst = false;
                }
            }
            elevator.outPut();
            elevator.setRequest(request);
            delDuplicateRequest(timeBefore);
            elevator.setMoveDirection();
            elevator.setFinishTime(getFinishTime(request));
            timeBefore = request.getRequestTime();
        }
        elevator.outPut();
        Elevator.printErrorInformation();
    }

    public double getFinishTime(Request request) {
        if (request.getRequestTime() > finishTime) {
            startTime = request.getRequestTime();
            finishTime = startTime + Math.abs(request.getTargetFloor() - startFloor)
                    * Floor.FLOOR_INTERVAL + Elevator.OPEN_CLOSE_INTERVAL;
        } else {
            startTime = finishTime;
            finishTime = startTime + Math.abs(request.getTargetFloor() - startFloor)
                    * Floor.FLOOR_INTERVAL + Elevator.OPEN_CLOSE_INTERVAL;
        }
        return finishTime;
    }

    public void delDuplicateRequest(int timeBefore) {
        Request currentRequest = elevator.getRequest();
        Request comparedRequest = new Request();
        for (int index = requestQueue.getRequestIndex(); index < requestQueue.getSizeOfQueue(); index++) {
            comparedRequest.manageRequest(requestQueue.getRequestAt(index), timeBefore, false);
            if (comparedRequest.isLegalRequest()) {
                if (currentRequest.getMode().equals("FR")) {
                    if (comparedRequest.getMode().equals("FR")) {
                        if (comparedRequest.getTargetFloor() == currentRequest.getTargetFloor() && comparedRequest.getMoveCondition() == currentRequest.getMoveCondition()) {
                            index = judgeInterval(currentRequest, comparedRequest, index);
                        }
                    }
                } else if (currentRequest.getMode().equals("ER")) {
                    if (comparedRequest.getMode().equals("ER")) {
                        if (currentRequest.getTargetFloor() == comparedRequest.getTargetFloor()) {
                            index = judgeInterval(currentRequest, comparedRequest, index);
                        }
                    }
                }
            }
        }
    }

    public int judgeInterval(Request first, Request second, int index) {
        double interval = Math.abs(first.getTargetFloor() - second.getTargetFloor()) * Floor.FLOOR_INTERVAL + Elevator.OPEN_CLOSE_INTERVAL;
        if (Math.abs(first.getRequestTime() - second.getRequestTime()) <= interval) {
            requestQueue.delRequestAt(index);
            index--;
        }
        return index;
    }

}
