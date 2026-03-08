package BT5;

public class Main {
    public static void main(String[] args) {
        try {
            User user = new User("Thanh", -5);
            user.display();
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi đăng ký người dùng:");
            e.printStackTrace();
        }
        System.out.println("Chương trình vẫn tiếp tục chạy.");
    }
}