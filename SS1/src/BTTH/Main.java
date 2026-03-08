package BTTH;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Nhập tên: ");
            String name = scanner.nextLine();
            System.out.print("Nhập tuổi: ");
            String age = scanner.nextLine();
            System.out.print("Nhập email: ");
            String email = scanner.nextLine();
            String userData = UserService.registerUser(name, age, email);
            System.out.println("Đăng ký thành công!");
            UserService.saveUserToFile(userData);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất luồng xử lý đăng ký.");
            scanner.close();
        }
    }
}