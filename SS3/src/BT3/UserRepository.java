package BT3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    static List<User> users = new ArrayList<>();
    static {
        users.add(new User("alice", "alice@gmail.com"));
        users.add(new User("bob", "bob@yahoo.com"));
        users.add(new User("charlie", "charlie@gmail.com"));
    }
    public static Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }
}
