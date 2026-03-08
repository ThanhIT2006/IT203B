import java.util.Scanner;

public class BT1 {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        try {
            System.out.println("Nhập năm sinh của bạn: ");
            String dateOfBirth = scanner.nextLine();

            int birthYear = Integer.parseInt(dateOfBirth);
            int age = 2026 - birthYear;
            System.out.println("Số tuổi của bạn là: " + age);
        }
        catch (NumberFormatException e) {
            System.err.println("--- Bạn chỉ có thể nhập số cho năm sinh ---");
        }
        finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
