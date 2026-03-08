package BTTH;

import java.io.FileNotFoundException;

public class UserService {
    public static String registerUser(String name, String ageInput, String email)
            throws InvalidAgeException, InvalidEmailException {
        int age;
        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Tuổi phải là một con số!");
        }
        if (age < 18) {
            throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi không đủ để đăng ký hệ thống.");
        }
        if (!email.contains("@")) {
            throw new InvalidEmailException("Lỗi nghiệp vụ: Email không hợp lệ.");
        }
        return name + "," + age + "," + email;
    }
    public static void saveUserToFile(String userData) throws FileNotFoundException {
        throw new FileNotFoundException("Không tìm thấy file lưu trữ.");
    }
}