package BT4;
/*1. Lãng phí tài nguyên khi dùng Statement
 Mỗi lần thực thi, DB phải parse và tạo Execution Plan lại từ đầu
 Lặp 1000 lần → xử lý 1000 lần → gây chậm và tốn tài nguyên
 */
import Util.DatabaseConnection;
import java.sql.*;

public class Main {
    public void insertLabResults() {
        String sql = "INSERT INTO lab_results (patient_id, test_name, result_value) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 1; i <= 1000; i++) {
                ps.setString(1, "P" + i);
                ps.setString(2, "Blood Test");
                ps.setDouble(3, 5.5 + i);
                ps.executeUpdate();
            }
            System.out.println("Insert thành công 1000 bản ghi");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        long start = System.currentTimeMillis();
        main.insertLabResults();
        long end = System.currentTimeMillis();
        System.out.println("Thời gian chạy: " + (end - start) + " ms");
    }
}
