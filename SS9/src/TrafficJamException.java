// Exception khi kẹt xe
public class TrafficJamException extends Exception {

    // Constructor truyền message
    public TrafficJamException(String msg) {
        super(msg);
    }
}