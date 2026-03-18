public class Truck extends Vehicle {

    // priority trung bình
    public Truck(String id, int speed) {
        super(id, speed, 5);
    }

    @Override
    public void move() {

        if (currentLight.equals("RED")) {
            Logger.log(getDisplayName() + " dừng (đèn đỏ)");
            return;
        }

        if (currentLight.equals("YELLOW")) {
            Logger.log(getDisplayName() + " chuẩn bị dừng");
            return;
        }

        // GREEN
        Logger.log(getDisplayName() + " đang đi");
    }

    @Override
    public String getDisplayName() {
        return "Xe tải " + formatId();
    }
}