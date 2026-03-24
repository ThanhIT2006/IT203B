package BT1;

/*
 PHÂN TÍCH LỖI:
 Trong JDBC, mặc định Connection có chế độ autoCommit = true
 => Nghĩa là: MỖI câu lệnh SQL sẽ được commit NGAY LẬP TỨC sau khi executeUpdate()
 Quy trình thực tế của đoạn code lỗi:
 Bước 1:
   UPDATE Medicine_Inventory ...
   => executeUpdate() chạy xong
   => AUTO-COMMIT kích hoạt -> dữ liệu bị ghi vĩnh viễn vào DB
 Bước 2:
   INSERT INTO Prescription_History ...
   => Nếu tại đây xảy ra lỗi (mất mạng, crash DB, exception...)
 => LÚC NÀY:
   - Lệnh UPDATE đã commit rồi -> KHÔNG rollback được
   - Lệnh INSERT bị fail
 => Kết quả:
   Kho bị trừ thuốc
   Nhưng không có lịch sử cấp phát
 => Vi phạm tính Atomicity của Transaction:
   "Hoặc tất cả thành công, hoặc tất cả thất bại"
 NGUYÊN NHÂN GỐC:
   - Không tắt autoCommit
   - Không dùng commit() / rollback()
*/
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    public static void capPhatThuoc(int medicineId, int patientId) {
        Connection conn = null;
        try {
            conn = DBConnection.openConnection();
            // Tắt Auto commit
            conn.setAutoCommit(false);

            // Trừ thuốc
            String sql1 = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();

            // Lưu lịch sử
            String sql2 = "INSERT INTO Prescription_History(patient_id, medicine_id, date) VALUES (?, ?, NOW())";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();

            // Commit
            conn.commit();
        } catch (Exception e) {
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
        capPhatThuoc(1, 101);
    }
}