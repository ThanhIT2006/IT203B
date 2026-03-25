package Session14;

import Session14.entity.OrderDetail;
import Session14.service.OrderService;
import Session14.utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws Exception {

        OrderService service = new OrderService();

        int totalThreads = 50;

        CountDownLatch latch = new CountDownLatch(1);

        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= totalThreads; i++) {

            int userId = i;

            Thread t = new Thread(() -> {
                try {
                    latch.await();

                    List<OrderDetail> list = new ArrayList<>();
                    list.add(new OrderDetail(1, 1, 100));

                    // dùng kết quả trả về để đếm đúng
                    if (service.placeOrder(userId, list)) {
                        success.incrementAndGet();
                    } else {
                        fail.incrementAndGet();
                    }

                } catch (Exception e) {
                    fail.incrementAndGet();
                }
            });

            threads.add(t);
            t.start();
        }

        System.out.println("=== START TEST 50 THREADS ===");
        latch.countDown();

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("=== DONE ===");
        System.out.println("SUCCESS: " + success.get());
        System.out.println("FAIL: " + fail.get());

        checkFinalStock();
    }

    // kiểm tra tồn kho
    public static void checkFinalStock() {
        try (Connection conn = DataConnect.openConnection()) {

            String sql = "select quantity from products where id = 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt(1);

                System.out.println("FINAL STOCK = " + stock);

                if (stock < 0) {
                    System.out.println("LỖI: ÂM KHO");
                } else {
                    System.out.println("KHÔNG ÂM KHO");
                }
            }

        } catch (Exception e) {
            System.out.println("Check lỗi: " + e.getMessage());
        }
    }
}