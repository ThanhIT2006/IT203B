package BT2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public record User(String username, String email) {
    }
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("alice", "alice@gmail.com"));
        users.add(new User("bob", "bob@yahoo.com"));
        users.add(new User("charlie", "charlie@gmail.com"));

        users.stream()
                .filter(user -> user.email().endsWith("@gmail.com"))
                .map(User::username)
                .forEach(System.out::println);
    }
}