package BT5;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User thực hiện: " + activity);
    }
}