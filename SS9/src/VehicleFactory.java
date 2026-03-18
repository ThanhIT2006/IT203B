import java.util.Random;

// Factory Pattern → tách logic tạo object
public class VehicleFactory {

    // Random để sinh xe ngẫu nhiên
    private static final Random random = new Random();

    public static Vehicle createVehicle() {

        // Chọn loại xe (0,1,2,3)
        int type = random.nextInt(4);

        switch (type) {

            case 0:
                // Tạo ô tô
                return new StandardVehicle("Car-" + random.nextInt(100), 60);

            case 1:
                // Tạo xe máy
                return new StandardVehicle("Bike-" + random.nextInt(100), 40);

            case 2:
                // Tạo xe cứu thương
                return new PriorityVehicle("Ambulance-" + random.nextInt(100), 80);
            case 3:
                //Tạo xe tải
                return new Truck("Truck-" + random.nextInt(100), 50);
            default:
                // Trường hợp không hợp lệ
                throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}