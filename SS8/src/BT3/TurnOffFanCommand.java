package BT3;

public class TurnOffFanCommand implements Command {
    Fan fan;

    public TurnOffFanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void undo() {
        fan.on();
    }

    @Override
    public void execute() {
        fan.off();
    }
}
