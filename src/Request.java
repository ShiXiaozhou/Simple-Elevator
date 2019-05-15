public class Request {
    private MoveCondition moveCondition;
    private int requestTime;
    private int targetFloor;
    private String mode;
    private boolean legalRequest = true;

    public void manageRequest(String request, int timeBefore, boolean errorFlag) {
        if (request.length() == 0) {
            System.out.println("Blank rows are not allowed!");
            System.exit(0);
        } else if (request.charAt(0) != '(' || request.charAt(request.length() - 1) != ')') {
            generateError();
            addErrorInformation(request, errorFlag);
        } else {
            String[] args = request.substring(1, request.length() - 1).split(",");
            try {
                if (args[0].equals("ER")) {
                    mode = args[0];
                    moveCondition = MoveCondition.UP;
                    targetFloor = Integer.parseInt(args[1]);
                    requestTime = Integer.parseInt(args[2]);
                    if (targetFloor > 10 || targetFloor < 1) {
                        generateError();
                        addErrorInformation(request, errorFlag);
                    }
                    if (requestTime < timeBefore) {
                        legalRequest = false;
                    }
                } else if (args[0].equals("FR")) {
                    mode = args[0];
                    moveCondition = MoveCondition.DOWN;
                    targetFloor = Integer.parseInt(args[1]);
                    requestTime = Integer.parseInt(args[3]);
                    if (args[2].equals("UP") && targetFloor < Floor.MAX_FLOOR) {
                        moveCondition = MoveCondition.UP;
                    } else if (args[2].equals("DOWN") && targetFloor > Floor.MIN_FLOOR) {
                        moveCondition = MoveCondition.DOWN;
                    } else {
                        generateError();
                        addErrorInformation(request, errorFlag);
                    }
                    if (targetFloor > 10 || targetFloor < 1) {
                        generateError();
                        addErrorInformation(request, errorFlag);
                    }
                    if (requestTime < timeBefore) {
                        legalRequest = false;
                    }
                } else {
                    generateError();
                    addErrorInformation(request, errorFlag);
                }
            } catch (Exception e) {
                generateError();
                addErrorInformation(request, errorFlag);
            }
        }
    }

    private void generateError() {
        moveCondition = MoveCondition.ERROR;
        requestTime = -1;
        targetFloor = 0;
        legalRequest = false;
    }

    private void addErrorInformation(String request, boolean addOrNot) {
        if (addOrNot) {
            Elevator.errorInformation.add("ERROR\n#" + request);
        }
    }

    public MoveCondition getMoveCondition() {
        return moveCondition;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public boolean isLegalRequest() {
        return legalRequest;
    }

    public String getMode() {
        return mode;
    }
}
