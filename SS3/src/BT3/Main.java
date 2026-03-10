package BT3;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<User> user = UserRepository.findUserByUsername("alice");
        user.ifPresent(u -> System.out.println("Welcome " + u.username()));
        String message = user.map(u -> "Welcome " + u.username()).orElse("Guest login");
        System.out.println(message);
    }
}
