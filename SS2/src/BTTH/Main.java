package BTTH;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User newUser = UserManagement.createUser.get();
        System.out.println("Tạo User bằng Supplier: " + newUser);

        String username = "thanhtest";
        boolean valid = IUserAccount.isStandardLength(username);
        System.out.println("Username hợp lệ: " + valid);

        User u1 = new User("thanh01","thanh@gmail.com","ADMIN","ACTIVE");
        User u2 = new User("dung02","dung@gmail.com","USER","ACTIVE");
        User u3 = new User("phuong03","phuong@gmail.com","USER","INACTIVE");
        User u4 = new User("hung04","hung@gmail.com","ADMIN","ACTIVE");

        String email = UserManagement.getEmail.apply(u1);
        System.out.println("Email của user: " + email);

        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        System.out.println("Danh sách User:");
        users.forEach(System.out::println);
    }
}