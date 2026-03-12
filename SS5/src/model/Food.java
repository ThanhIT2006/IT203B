package model;

public class Food extends MenuItem {
    private int calories;

    public Food(String id, String name, double basePrice, int calories) {
        super(id, name, basePrice);
        this.calories = calories;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Calo: %d kcal", calories);
    }
}