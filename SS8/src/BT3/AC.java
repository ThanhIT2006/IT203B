package BT3;

public class AC {
    private int temp = 25;

    public AC(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
    public void changeTemp(int newTemp){
        temp = newTemp;
    }
}
