package BT1;

public class AC implements Device {
    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bật.");
    }

    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}