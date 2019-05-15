import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter all the requests end by \"RUN\"");
        Scanner scanner = new Scanner(System.in);
        RequestQueue requestQueue = new RequestQueue();

        String request = scanner.nextLine().replaceAll(" ", ""); //ignore whitespace
        while (!request.equals("RUN")) {
            requestQueue.addRequest(request);
            request = scanner.nextLine().replaceAll(" ", "");
        }

        Dispatcher dispatcher = new Dispatcher(requestQueue);
        dispatcher.runElevator();

        System.out.println("The elevator program has exited, thanks for using.");
    }
}
