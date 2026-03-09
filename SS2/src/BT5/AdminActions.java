package BT5;

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin thực hiện: " + activity);
    }
}
