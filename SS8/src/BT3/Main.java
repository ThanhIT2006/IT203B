package BT3;

public class Main {
    public static void main(String[] args) {

        Light light = new Light();
        Fan fan = new Fan();
        AC ac = new AC(25);

        RemoteControl remoteControl = new RemoteControl(); // tạo 1 lần

        do {
            System.out.println("""
                    1. Bật đèn
                    2. Tắt đèn
                    3. Bật quạt
                    4. Tắt quạt
                    5. Điều chỉnh nhiệt độ
                    6. Undi
                    7. Thoát
                    Nhập lựa chọn của bạn:
                    """);

            int choice = Integer.parseInt(System.console().readLine());

            switch (choice) {
                case 1:
                    remoteControl.addCommand(1, new TurnOnLightCommand(light));
                    remoteControl.pressButton(1);
                    break;

                case 2:
                    remoteControl.addCommand(2, new TurnOffLightCommand(light));
                    remoteControl.pressButton(2);
                    break;

                case 3:
                    remoteControl.addCommand(3, new TurnOnFanCommand(fan));
                    remoteControl.pressButton(3);
                    break;

                case 4:
                    remoteControl.addCommand(4, new TurnOffFanCommand(fan));
                    remoteControl.pressButton(4);
                    break;

                case 5:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = Integer.parseInt(System.console().readLine());

                    remoteControl.addCommand(5, new ACCommand(ac, temp));
                    remoteControl.pressButton(5);
                    break;

                case 6:
                    System.out.println("Undo thao tác gần nhất");
                    remoteControl.pressBack();
                    break;

                case 7:
                    System.out.println("Thoát");
                    return;

                default:
                    System.err.println("Nhập sai lựa chọn!");
            }

        } while (true);
    }
}