package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private int clientId;
    private ArrayList<Product> products;
    private float totalPrice;
    private String address;
    private LocalDate orderDate;

    public Order(int clientId, ArrayList<Product> products, float totalPrice, String address, LocalDate orderDate) {
        this.products = new ArrayList<>();
        this.clientId = clientId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderDate = orderDate;
    }

    public Order(int clientId, float totalPrice, String address, LocalDate orderDate) {
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderDate = orderDate;
    }

    public Order(int orderId, int clientId, float totalPrice, String address, LocalDate orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", adress='" + address + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
