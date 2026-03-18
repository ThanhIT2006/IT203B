package BT2;

public class Main {
    public static void main(String[] args) {

        OldThermometer oldThermometer = new OldThermometer();
        TemperatureSensor adapter = new ThermometerAdapter(oldThermometer);

        SmartHomeFacade home = new SmartHomeFacade(adapter);

        System.out.println("1. Xem nhiệt độ");
        home.getCurrentTemperature();

        System.out.println("\n2. Chế độ rời nhà");
        home.leaveHome();

        System.out.println("\n3. Chế độ ngủ");
        home.sleepMode();
    }
}