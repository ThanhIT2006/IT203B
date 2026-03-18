import java.util.concurrent.*;

// Class điều phối toàn hệ thống
public class SimulationEngine {

    public void start() {

        // Tạo đèn giao thông
        TrafficLight light = new TrafficLight();

        // Tạo thread cho đèn
        Thread t = new Thread(light);

        // Set daemon → chương trình kết thúc thì thread tự tắt
        t.setDaemon(true);

        // Start đèn
        t.start();

        // Tạo giao lộ
        Intersection intersection = new Intersection(light);

        // Bắt đầu xử lý xe
        intersection.process();

        // Scheduler để tạo xe định kỳ
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Mỗi 1 giây tạo 1 xe
        scheduler.scheduleAtFixedRate(() -> {
            try {

                Vehicle v = VehicleFactory.createVehicle();

                // ✅ chỉ gọi cái này thôi
                intersection.addVehicle(v);

            } catch (Exception e) {

                // ✅ có time luôn
                Logger.log("KẸT XE!");
            }

        }, 0, 1, TimeUnit.SECONDS);

        // Dừng mô phỏng sau 15 giây
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {

            System.out.println("===== KẾT THÚC MÔ PHỎNG =====");

            scheduler.shutdownNow();
            intersection.stop();
            light.stop();

            intersection.stats();

            System.exit(0);

        }, 15, TimeUnit.SECONDS);
    }
}