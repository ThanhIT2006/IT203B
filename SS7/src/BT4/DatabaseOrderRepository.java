package BT4;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOrderRepository implements OrderRepository {

    List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào database: " + order.orderId);
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

}