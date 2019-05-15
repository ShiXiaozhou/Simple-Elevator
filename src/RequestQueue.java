import java.util.ArrayList;
import java.util.List;

public class RequestQueue {
    private List<String> requests;
    private int requestIndex;

    public RequestQueue() {
        requestIndex = 0;
        requests = new ArrayList<>();
    }

    public void addRequest(String request) {
        this.requests.add(request);
    }

    public int getRequestIndex() {
        return requestIndex;
    }

    public int getSizeOfQueue() {
        return requests.size();
    }

    public boolean hasNext() {
        return requestIndex < requests.size();
    }

    public String getRequestNext() {
        return requests.get(requestIndex++);
    }

    public String getRequestAt(int position) {
        return requests.get(position);
    }

    public void delRequestAt(int position) {
        requests.remove(position);
    }
}
