package BT4;

class User {
    private String username;

    public User() {
        this.username = "User mặc định";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}