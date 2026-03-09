package BTTH;

public interface IUserAccount {
    String getRole();
    default boolean checkAccess() {
        if (getRole().equals("ADMIN")) {
            return true;
        }
        return false;
    }
    static boolean isStandardLength(String username) {
        return username != null && username.length() > 5;
    }
}