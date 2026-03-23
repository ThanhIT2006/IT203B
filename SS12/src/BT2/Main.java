package BT2;
/*
 * PreparedStatement với setDouble(), setInt() truyền dữ liệu dạng số,
 * JDBC tự xử lý định dạng nên không phụ thuộc dấu . hay , của hệ điều hành.
 */

import Util.DatabaseConnection;
import java.sql.*;

public class Main {

    public void updateVitalSigns(String patientId, double temperature, int heartRate) {

        String sql = "UPDATE patients SET temperature = ?, heart_rate = ? WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, temperature);
            ps.setInt(2, heartRate);
            ps.setString(3, patientId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Không tìm thấy bệnh nhân");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.updateVitalSigns("P001", 37.5, 80);
    }
}
