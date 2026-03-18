public class RedState implements LightState {

    @Override
    public void handle(TrafficLight light) {
        light.setState(new GreenState());
    }

    @Override
    public String getColor() {
        return "RED";
    }

    @Override
    public int getDuration() {
        return 3000; // 3 giây
    }
}