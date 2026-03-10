package BT5;

import java.util.List;
import java.util.Comparator;

public class Main {
    public record User(String username, String email) {
    }
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander", "alex@gmail.com"),
                new User("charlotte", "charlotte@gmail.com"),
                new User("Benjamin", "ben@gmail.com"),
                new User("bob", "bob@gmail.com"),
                new User("anna", "anna@gmail.com")
        );
        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .map(User::username)
                .forEach(System.out::println);
    }
}
