public class GreenState implements LightState {

    @Override
    public void handle(TrafficLight light) {
        light.setState(new YellowState());
    }

    @Override
    public String getColor() {
        return "GREEN";
    }

    @Override
    public int getDuration() {
        return 3000; // 3 giây
    }
}