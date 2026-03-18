import java.util.concurrent.*;

// Intersection = vùng giao nhau (Critical Section)
public class Intersection {

    // PriorityBlockingQueue:
    // - Thread-safe
    // - Tự sắp xếp theo priority (xe cứu thương lên trước)
    private final PriorityBlockingQueue<Vehicle> queue = new PriorityBlockingQueue<>();
    // lưu loại xe đã qua để thống kê bằng Stream
    private final ConcurrentLinkedQueue<Vehicle> passedVehicles = new ConcurrentLinkedQueue<>();
    // Semaphore:
    // - Giới hạn số xe vào giao lộ cùng lúc
    // - Ở đây = 1 → tránh va chạm
    private final Semaphore semaphore = new Semaphore(1);

    // Sức chứa tối đa → dùng để detect kẹt xe
    private final int MAX_CAPACITY = 5;

    // Thống kê số xe đã đi qua
    private int passed = 0;

    // Thống kê số lần kẹt xe
    private int trafficJam = 0;

    // Tham chiếu đến TrafficLight
    private final TrafficLight light;

    // biến điều khiển chạy/dừng
    private volatile boolean running = true;

    // Constructor
    public Intersection(TrafficLight light) {
        this.light = light;
    }

    // Thêm xe vào hàng đợi
    public void addVehicle(Vehicle v) throws TrafficJamException {

        // VALIDATE null
        if (v == null) {
            throw new IllegalArgumentException("Vehicle null");
        }

        // Nếu queue đầy → kẹt xe
        if (queue.size() >= MAX_CAPACITY) {

            // tăng số lần kẹt xe
            trafficJam++;

            // ném exception
            throw new TrafficJamException("Jam!");
        }

        // Gắn xe vào observer của đèn
        // → để xe nhận tín hiệu đèn
        light.attach(v);

        // Đưa xe vào queue
        queue.offer(v);

        Logger.log(v + " vào hàng chờ");
    }

    // Xử lý xe đi qua giao lộ
    public void process() {

        // Tạo thread riêng để xử lý
        new Thread(() -> {

            // chạy đến khi stop
            while (running) {
                try {

                    // Lấy xe ra (nếu queue rỗng → sẽ chờ)
                    Vehicle v = queue.take();

                    // Nếu là xe cứu thương → đi luôn
                    if (v instanceof PriorityVehicle) {
                        // bỏ qua đèn
                    } else {

                        // ❗ Nếu đèn đỏ → KHÔNG cho đi
                        while (v.currentLight.equals("RED") && running) {
                            Thread.sleep(200);
                        }

                        // ❗ Nếu đèn vàng → cho dừng (không đi tiếp)
                        if (v.currentLight.equals("YELLOW")) {
                            queue.offer(v); // đưa lại queue
                            Thread.sleep(200);
                            continue;
                        }
                    }

                    // Acquire quyền vào giao lộ
                    semaphore.acquire();

                    // Cho xe chạy trước
                    new Thread(v).start();

                    // Delay nhỏ để move() log trước
                    Thread.sleep(50);

                    // Sau đó mới log đi qua
                    Logger.log(v + " đang đi qua ngã tư");

                    // Giả lập thời gian đi qua
                    Thread.sleep(1000);

                    // Tăng số xe đã qua
                    passed++;
                    passedVehicles.add(v);

                    // Release → cho xe khác vào
                    semaphore.release();

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Dừng giao lộ
    public void stop() {
        running = false;
    }

    public void stats() {

        System.out.println("Passed: " + passed);
        System.out.println("Jam: " + trafficJam);

        System.out.println("=== Vehicle Stats ===");

        passedVehicles.stream()
                .map(v -> v.getDisplayName()) // dùng displayName luôn
                .map(name -> name.split(" #")[0]) // lấy "Xe ô tô", "Xe tải", ...
                .distinct()
                .forEach(type -> {

                    long count = passedVehicles.stream()
                            .map(v -> v.getDisplayName())
                            .filter(name -> name.startsWith(type))
                            .count();

                    System.out.println(type + ": " + count);
                });
    }
}