import java.io.IOException;

public class BT4 {
    public static void saveToFile() throws IOException {
        System.out.println("Method C: Đang lưu dữ liệu vào file...");
        throw new IOException("Lỗi ghi file!");
    }
    public static void processUserData() throws IOException {
        System.out.println("Method B: Đang xử lý dữ liệu người dùng...");
        saveToFile();
    }
    public static void main(String[] args) {
        try {
            System.out.println("Main: Bắt đầu chương trình...");
            processUserData();
        } catch (IOException e) {
            System.out.println("Main: Đã bắt được lỗi!");
            System.out.println("Thông báo lỗi: " + e.getMessage());
        }
        System.out.println("Chương trình vẫn tiếp tục chạy.");
    }
}