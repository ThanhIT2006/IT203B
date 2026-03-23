package BT1;

/* 1. Vì sao PreparedStatement chống SQL Injection:
 Không nối chuỗi SQL với dữ liệu đầu vào.
 Sử dụng dấu ? để truyền tham số.
 Dữ liệu người dùng được xử lý như giá trị thuần, không phải lệnh SQL.
 => Ngăn chặn việc chèn SQL độc hại.
 2. Vai trò của Pre-compiled:
 Câu SQL được biên dịch trước với cấu trúc cố định.
 Tham số chỉ được gán giá trị sau khi biên dịch.
 => Người dùng không thể thay đổi cấu trúc câu lệnh.
 */

import Util.DatabaseConnection;
import java.sql.*;

public class Main {
    public boolean login(String doctorCode, String password) {
        boolean isValid = false;
        String sql = "SELECT * FROM doctors WHERE doctor_code = ? AND password = ?";
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doctorCode);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return isValid;
    }
    public static void main(String[] args) {
        Main m = new Main();
        boolean result = m.login("D001", "123456");
        if (result) {
            System.out.println("Đăng nhập thành công");
        } else {
            System.out.println("Đăng nhập thất bại");
        }
    }
}