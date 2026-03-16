package BT2;

public class OrderCalculator {

    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateTotal(double amount) {
        return discountStrategy.applyDiscount(amount);
    }
}
