// Xe ưu tiên (xe cứu thương)
public class PriorityVehicle extends Vehicle {

    // Constructor → priority cao (10)
    public PriorityVehicle(String id, int speed) {
        super(id, speed, 10);
    }


    @Override
    public String getDisplayName() {
        return "Xe cứu thương " + formatId();
    }
    // Override hành vi di chuyển
    @Override
    public void move() {
        Logger.log(getDisplayName() + " đang ưu tiên!");
    }
}