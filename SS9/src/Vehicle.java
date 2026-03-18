// Vehicle vừa là:
// - Runnable (thread)
// - Comparable (priority queue)
// - Observer (nhận tín hiệu đèn)
public abstract class Vehicle implements Runnable, Comparable<Vehicle>, Observer {

    protected String id;
    protected int speed;
    protected int priority;

    // trạng thái đèn mà xe đang "nhìn thấy"
    protected volatile String currentLight = "RED";

    public Vehicle(String id, int speed, int priority) {

        // validate id
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID null");
        }

        // validate speed
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must > 0");
        }

        this.id = id;
        this.speed = speed;
        this.priority = priority;
    }

    // Nhận tín hiệu từ TrafficLight
    @Override
    public void update(String color) {

        // cập nhật trạng thái đèn mà xe thấy
        this.currentLight = color;
    }

    public abstract void move();

    @Override
    public void run() {
        move();
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    // So sánh priority (xe ưu tiên lên trước)
    @Override
    public int compareTo(Vehicle o) {
        return Integer.compare(o.priority, this.priority);
    }
    @Override
    public String toString() {
        return getDisplayName();
    }
    // Format số: #01, #02,...
    protected String formatId() {
        try {
            int num = Integer.parseInt(id.split("-")[1]);
            return String.format("#%02d", num);
        } catch (Exception e) {
            return id; // fallback nếu lỗi format
        }
    }

    // Tên hiển thị (sẽ override ở subclass)
    public String getDisplayName() {
        return "Xe " + formatId();
    }
}
