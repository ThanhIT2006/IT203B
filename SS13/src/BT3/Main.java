package BT3;

/*
- Phân tích bài toán (I/O)
    Input:
    maBenhNhan → int (ID bệnh nhân)
    tienVienPhi → double (số tiền cần thanh toán)
    Output:
    Thành công:
        "Xuất viện và thanh toán thành công"
    Thất bại:
        "Lỗi: <message>"
    Đồng thời đảm bảo:
    Không thay đổi dữ liệu nếu có lỗi
    Không bị trừ tiền sai
    Không bị treo giường
- Đề xuất giải pháp
    Dùng Transaction trong JDBC
    Tắt autoCommit
    Bao toàn bộ logic trong try-catch
    Nguyên tắc:
        Thành công → commit()
        Có lỗi → rollback()
    Xử lý 2 bẫy:
        Bẫy 1 (thiếu tiền) → check trước khi update → throw Exception
        Bẫy 2 (row = 0) → kiểm tra executeUpdate() → nếu = 0 → throw Exception
- Thiết kế các bước
+ Mở connection
+ Tắt autoCommit
+ Lấy số dư bệnh nhân
+ Kiểm tra đủ tiền (Bẫy 1)
+ Trừ tiền
+ Update giường
+ Update trạng thái bệnh nhân
+ Kiểm tra row affected (Bẫy 2)
+ Commit nếu OK
+ Rollback nếu lỗi
+ Đóng connection
 */

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;

        try {
            conn = DBConnection.openConnection();
            conn.setAutoCommit(false);

            // Lấy số dư
            String sqlCheck = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, maBenhNhan);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Bệnh nhân không tồn tại!");
            }

            double balance = rs.getDouble("balance");

            // Bẫy 1: Thiếu tiền
            if (balance < tienVienPhi) {
                /*
                 BẪY 1:
                 - Không đủ tiền
                 - Nếu vẫn trừ -> số dư âm (sai nghiệp vụ)

                 => PHẢI chặn lại
                 => Ném exception để rollback toàn bộ
                */
                throw new RuntimeException("Không đủ tiền trong ví!");
            }

            // Trừ tiền
            String sql1 = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1, tienVienPhi);
            ps1.setInt(2, maBenhNhan);
            int row1 = ps1.executeUpdate();

            // Giải phóng giường
            String sql2 = "UPDATE Beds SET status = 'EMPTY' WHERE patient_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, maBenhNhan);
            int row2 = ps2.executeUpdate();

            // Update bệnh nhân
            String sql3 = "UPDATE Patients SET status = 'DISCHARGED' WHERE patient_id = ?";
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setInt(1, maBenhNhan);
            int row3 = ps3.executeUpdate();

            // Bẫy 2: Row Affected = 0
            if (row1 == 0 || row2 == 0 || row3 == 0) {
                /*
                 BẪY 2:
                 - executeUpdate() = 0 nghĩa là:
                   Không có dòng nào được update

                 => Có thể:
                    - Sai ID
                    - Dữ liệu không tồn tại

                 JDBC không tự báo lỗi
                 => Nếu commit -> dữ liệu sai lệch

                 => Phải chủ động throw exception
                */
                throw new RuntimeException("Cập nhật thất bại! Dữ liệu không tồn tại");
            }
            // commit
            conn.commit();
        } catch (Exception e) {

            // roll back
            try {
                if (conn != null) {
                    conn.rollback();
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
        xuatVienVaThanhToan(101, 500000);
    }
}