import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// TrafficLight đóng vai trò:
// 1. Context trong State Pattern
// 2. Subject trong Observer Pattern
// 3. Runnable → chạy như 1 thread riêng
public class TrafficLight implements Runnable {

    // State hiện tại (Green, Yellow, Red)
    private LightState currentState;

    // Danh sách observer (các xe đang "nghe" đèn)
    // CopyOnWriteArrayList → thread-safe (an toàn khi nhiều thread đọc/ghi)
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    // biến điều khiển chạy/dừng
    private volatile boolean running = true;

    // Constructor → khởi tạo trạng thái ban đầu là RED
    public TrafficLight() {
        currentState = new RedState();
    }

    // Đăng ký observer (xe sẽ subscribe vào đây)
    public void attach(Observer o) {
        observers.add(o);
    }

    // Set state mới (được gọi từ State Pattern)
    public void setState(LightState state) {

        // cập nhật state hiện tại
        this.currentState = state;

        // khi state thay đổi → notify toàn bộ observer
        notifyObservers();
    }

    // Lấy màu hiện tại của đèn
    public String getColor() {
        return currentState.getColor();
    }

    // Thông báo tất cả xe khi đèn đổi
    private void notifyObservers() {

        // duyệt toàn bộ observer
        for (Observer o : observers) {

            // gọi update → xe nhận tín hiệu đèn
            o.update(getColor());
        }
    }

    // Dừng đèn giao thông
    public void stop() {
        running = false;
    }

    // Thread chạy vòng lặp đèn giao thông
    @Override
    public void run() {
        try {
            while (running) {

                // In trạng thái hiện tại (log)
                Logger.log("Đèn: " + getColor());

                // notify lần đầu
                notifyObservers();

                // Chờ theo thời gian của state (GREEN 3s, YELLOW 2s, RED 3s)
                Thread.sleep(currentState.getDuration());

                // Chuyển sang state tiếp theo
                currentState.handle(this);
            }
        } catch (InterruptedException e) {

            // Nếu thread bị interrupt → giữ trạng thái interrupt
            Thread.currentThread().interrupt();
        }
    }
}