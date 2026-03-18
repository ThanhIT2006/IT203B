public interface LightState {

    // Xử lý chuyển state
    void handle(TrafficLight light);

    // Trả về màu đèn
    String getColor();

    int getDuration(); // thời gian giữ state (ms)
}