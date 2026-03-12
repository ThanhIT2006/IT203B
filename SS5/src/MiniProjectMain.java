
import model.*;
import service.StoreManager;
import exception.FastFoodException;
import java.util.*;

public class MiniProjectMain {
    public static void main(String[] args) {
        StoreManager manager = new StoreManager();
        Scanner sc = new Scanner(System.in);

        manager.addToMenu(new Food("F01", "Burger Phô Mai", 55.0, 450));
        manager.addToMenu(new Food("F02", "Gà Rán Cay", 40.0, 350));
        manager.addToMenu(new Food("F03", "Khoai Tây Chiên", 25.0, 200));
        manager.addToMenu(new Drink("D01", "Pepsi", 15.0, "S"));
        manager.addToMenu(new Drink("D02", "Trà Đào", 25.0, "L"));

        System.out.println("=== CHƯƠNG TRÌNH QUẢN LÝ CỬA HÀNG (SESSION 05) ===");

        while (true) {
            System.out.println("\n--- MENU CHỨC NĂNG ---");
            System.out.println("1. Hiển thị thực đơn (Menu)");
            System.out.println("2. Tạo đơn hàng và Tính tiền");
            System.out.println("3. Tìm kiếm món ăn theo tên");
            System.out.println("0. Thoát hệ thống");
            System.out.print("Mời bạn chọn (0-3): ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("\n--- THỰC ĐƠN CỬA HÀNG ---");
                        manager.getMenu().forEach(System.out::println);
                        break;

                    case 2:
                        Map<MenuItem, Integer> cart = new HashMap<>();
                        System.out.println("\n--- TẠO ĐƠN HÀNG MỚI ---");
                        while (true) {
                            System.out.print("Nhập ID món (hoặc 'exit' để thanh toán): ");
                            String id = sc.nextLine();
                            if (id.equalsIgnoreCase("exit")) break;

                            try {
                                MenuItem item = manager.getItemById(id);
                                System.out.print("Nhập số lượng cho " + item.getName() + ": ");
                                int qty = Integer.parseInt(sc.nextLine());

                                cart.put(item, cart.getOrDefault(item, 0) + qty);
                                System.out.println("-> Đã thêm vào giỏ hàng.");
                            } catch (FastFoodException e) {
                                System.err.println("Lỗi: " + e.getMessage());
                            } catch (NumberFormatException e) {
                                System.err.println("Lỗi: Số lượng phải là số nguyên!");
                            }
                        }

                        if (!cart.isEmpty()) {
                            double total = manager.calculateTotal(cart);
                            System.out.println("\n----------------------------");
                            System.out.println("TỔNG HÓA ĐƠN: " + total + " VNĐ");
                            System.out.println("----------------------------");
                        } else {
                            System.out.println("Giỏ hàng trống!");
                        }
                        break;

                    case 3:
                        System.out.print("Nhập từ khóa tìm kiếm: ");
                        String keyword = sc.nextLine();
                        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
                        manager.searchByName(keyword).forEach(System.out::println);
                        break;

                    case 0:
                        System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập số!");
            }
        }
    }
}