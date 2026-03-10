package BT4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public record User(String username, String email) {
    }
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com"),
                new User("bob", "bob@gmail.com"),
                new User("alice", "alice2@gmail.com"),
                new User("charlie", "charlie@gmail.com"),
                new User("bob", "bob2@gmail.com")
        );
        Collection<User> uniqueUsers = users.stream()
                .collect(Collectors.toMap(
                        User::username,
                        user -> user,
                        (u1, u2) -> u1
                ))
                .values();
        uniqueUsers.forEach(System.out::println);
    }
}