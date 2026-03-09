package BT1;

import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Thanh", "ADMIN");
        Predicate<User> laAdmin = user -> user.getRole().equalsIgnoreCase("ADMIN");
        System.out.println("User có phải Admin không: " + laAdmin.test(user1));

        Function<User, String> layTenDangNhap = user -> user.getUsername();
        System.out.println("Tên đăng nhập của User: " + layTenDangNhap.apply(user1));

        Consumer<User> inThongTinUser = user ->
                System.out.println("Thông tin chi tiết của User: " + user);
        inThongTinUser.accept(user1);

        Supplier<User> taoUserMacDinh = () -> new User("guest", "USER");
        User userMoi = taoUserMacDinh.get();
        System.out.println("User mới được khởi tạo: " + userMoi);
    }
}