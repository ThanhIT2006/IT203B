package BT3;

public class ACCommand implements Command{
    AC ac;
    int newTemp;
    int oldTemp;

    public ACCommand(AC ac, int newTemp) {
        this.ac = ac;
        this.newTemp = newTemp;
    }

    @Override
    public void undo() {
        ac.changeTemp(oldTemp);
        System.out.println("Đã trở lại nhiệt đọ cũ"+ ac.getTemp());
    }

    @Override
    public void execute() {
        this.oldTemp = ac.getTemp();
        ac.changeTemp(newTemp);
        System.out.println(oldTemp>newTemp?"Đang giảm nhiệt độ"+ac.getTemp(): "Đang tăng nhiệt độ"+ac.getTemp());
    }
}
