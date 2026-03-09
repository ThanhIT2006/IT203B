package BTTH;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UserManagement {
    public static Supplier<User> createUser = User::new;
    public static Predicate<User> isActive = user -> user.getStatus().equals("ACTIVE");
    public static Function<User, String> getEmail = user -> user.getEmail();
}