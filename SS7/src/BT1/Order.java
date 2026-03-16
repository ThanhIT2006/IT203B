package BT1;

import java.util.HashMap;
import java.util.Map;

public class Order {

    String orderId;
    Customer customer;
    Map<Product, Integer> products;
    double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}
