package BT5;

class SuperAdmin implements UserActions, AdminActions {
    @Override
    public void logActivity(String activity) {
        System.out.println("SuperAdmin ghi log:");

        UserActions.super.logActivity(activity);
        AdminActions.super.logActivity(activity);
    }
}