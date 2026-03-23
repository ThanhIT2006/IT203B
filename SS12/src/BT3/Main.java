package BT3;
/* 1. Vì sao phải registerOutParameter()?
 JDBC không tự biết tham số nào là OUT
 Phải đăng ký để xác định vị trí và kiểu dữ liệu của tham số OUT
 Nếu không → lỗi "column index is out of range" hoặc không lấy được dữ liệu
 2. Kiểu DECIMAL trong SQL ===
 Trong Java phải dùng: Types.DECIMAL
 */

import Util.DatabaseConnection;
import java.sql.*;

public class Main {
    public void getSurgeryFee(int surgeryId) {
        String sql = "{CALL GET_SURGERY_FEE(?, ?)}";

        try (Connection conn = DatabaseConnection.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, surgeryId);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.execute();
            double totalCost = cs.getDouble(2);
            System.out.println("Chi phí phẫu thuật: " + totalCost);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Main dao = new Main();
        dao.getSurgeryFee(1);
    }
}