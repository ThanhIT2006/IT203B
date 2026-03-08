package BT5;

public class User {
    private String name;
    private int age;
    public User(String name, int age) throws InvalidAgeException {
        this.name = name;
        setAge(age);
    }
    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Tuổi không hợp lệ cho đăng ký người dùng!");
        }
        this.age = age;
    }
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}