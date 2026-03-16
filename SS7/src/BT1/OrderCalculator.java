package BT1;

import java.util.Map;

public class OrderCalculator {

    public double calculateTotal(Order order) {

        double total = 0;
        //Duyệt qua toàn bộ sản phẩm
        for (Map.Entry<Product, Integer> entry : order.products.entrySet()) {

            Product product = entry.getKey();
            int quantity = entry.getValue();

            total += product.price * quantity;
        }

        return total;
    }
}
