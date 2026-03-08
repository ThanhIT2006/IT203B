package BT3;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-1);
            System.out.println("Tuổi: " + user.getAge());
        }
        catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Chương trình tiếp tục chạy...");
    }
}
