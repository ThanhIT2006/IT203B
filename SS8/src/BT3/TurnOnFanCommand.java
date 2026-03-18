package BT3;

public class TurnOnFanCommand implements Command{
    Fan fan;

    public TurnOnFanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void undo() {
        fan.off();
    }

    @Override
    public void execute() {
        fan.on();
    }
}
