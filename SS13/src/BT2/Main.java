package BT2;

/*
Vấn đề:
    - Dev đã dùng:
    + setAutoCommit(false)
    + commit() ở cuối
    => Nhưng trong khối catch chỉ có:
    System.out.println()
    Đây là sai nghiêm trọng trong Transaction
Vấn đề lớn:
    - Không gọi rollback()
    - Transaction vẫn đang "mở"
    - Connection giữ trạng thái chưa hoàn tất
    => Hậu quả:
    Connection bị treo (dangling transaction)
    Lock dữ liệu (có thể block query khác)
    Tốn tài nguyên DB
    Có nguy cơ gây lỗi dây chuyền
Dev đã quên hành động bắt buộc:
    conn.rollback();
 */

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static void thanhToanVienPhi(int patientId, double amount) {
        Connection conn = null;

        try {
            conn = DBConnection.openConnection();

            // Tắt auto commit
            conn.setAutoCommit(false);

            // Trừ tiền ví
            String sql1 = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();

            // Update hóa đơn
            String sql2 = "UPDATE Invoices SET status = 'PAID' WHERE patient_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, patientId);
            ps2.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Bắt buộc phải có
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.err.println(e.getMessage());

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }

    public static void main(String[] args) {
        thanhToanVienPhi(101, 500000);
    }
}