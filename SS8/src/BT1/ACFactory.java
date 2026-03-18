package BT1;

public class ACFactory extends DeviceFactory {
    @Override
    public Device createDevice() {
        System.out.println("ACFactory: Đã tạo điều hòa mới.");
        return new AC();
    }
}
