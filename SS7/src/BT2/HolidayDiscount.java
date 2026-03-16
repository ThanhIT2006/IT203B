package BT2;

public class HolidayDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * 15 / 100);
    }
}
