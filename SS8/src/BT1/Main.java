package BT1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Device> devices = new ArrayList<>();

        System.out.println("1. Kết nối phần cứng");
        System.out.println("(Chọn 1)");
        HardwareConnection conn1 = HardwareConnection.getInstance();

        System.out.println("\n2. Tạo thiết bị mới");
        System.out.println("Chọn loại: 1. Đèn, 2. Quạt, 3. Điều hòa");
        System.out.println("Chọn: 1");
        DeviceFactory factory1 = new LightFactory();
        Device device1 = factory1.createDevice();
        devices.add(device1);

        System.out.println("\n3. Bật thiết bị");
        System.out.println("Chọn thiết bị vừa tạo: 1");
        devices.get(0).turnOn();

        System.out.println("\n4. Tạo thêm thiết bị");
        System.out.println("Chọn loại: 2");
        DeviceFactory factory2 = new FanFactory();
        Device device2 = factory2.createDevice();
        devices.add(device2);

        System.out.println("\n5. Kiểm tra Singleton");
        System.out.println("Gọi kết nối lần 2");
        HardwareConnection conn2 = HardwareConnection.getInstance();
    }
}
