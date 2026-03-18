public class YellowState implements LightState {

    @Override
    public void handle(TrafficLight light) {
        light.setState(new RedState());
    }

    @Override
    public String getColor() {
        return "YELLOW";
    }

    @Override
    public int getDuration() {
        return 2000; // 2 giây
    }
}