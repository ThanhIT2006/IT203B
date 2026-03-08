import java.util.Scanner;

public class BT2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tổng số người dùng: ");
        int n = sc.nextInt();
        System.out.println("Nhập số người muốn chia: ");
        int m = sc.nextInt();
        try {
            int newGroup = n / m;
            System.out.println("Mỗi nhóm có: " + newGroup + " người");
        }
        catch (ArithmeticException e) {
            System.err.println("--- Không thể chia cho 0 ---");
        }
        System.out.println("Chương trình vẫn chạy");
    }
}
