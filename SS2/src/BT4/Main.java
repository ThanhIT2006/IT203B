package BT4;

import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Thanh"));
        users.add(new User("Dung"));
        users.add(new User("Phuong"));

        Function<User, String> layUsername = User::getUsername;

        System.out.println("Danh sách username:");
        users.stream().map(layUsername).forEach(System.out::println);

        Consumer<String> inChuoi = System.out::println;
        inChuoi.accept("Xin chào Java Method Reference");
        Supplier<User> taoUser = User::new;
        User userMoi = taoUser.get();
        System.out.println("User mới: " + userMoi.getUsername());
    }
}