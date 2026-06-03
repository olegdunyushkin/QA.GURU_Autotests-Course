package tests.files.model;

import java.util.List;

public class ShopOrder {

    public String orderId;
    public Customer customer;
    public List<OrderItem> items;
    public Delivery delivery;
    public boolean paid;
}
