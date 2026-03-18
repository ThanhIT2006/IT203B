public class StandardVehicle extends Vehicle {

    public StandardVehicle(String id, int speed) {
        super(id, speed, 1);
    }

    @Override
    public String getDisplayName() {

        if (id.startsWith("Car")) {
            return "Xe ô tô " + formatId();
        }

        if (id.startsWith("Bike")) {
            return "Xe máy " + formatId();
        }

        return "Xe thường " + formatId();
    }

    @Override
    public void move() {

        if (currentLight.equals("RED")) {
            Logger.log(getDisplayName() + " dừng (đèn đỏ)");
            return;
        }

        if (currentLight.equals("YELLOW")) {
            Logger.log(getDisplayName() + " chuẩn bị dừng (đèn vàng)");
            return;
        }

        Logger.log(getDisplayName() + " đang đi bình thường");
    }
}