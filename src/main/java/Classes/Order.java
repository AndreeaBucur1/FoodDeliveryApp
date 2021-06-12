package Classes;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private int orderId;
    private int clientId;
    private ArrayList<Product> products;
    private double totalPrice;
    private String address;
    private LocalDate orderDate;



    public Order(int clientId, ArrayList<Product> products, double totalPrice, String address, LocalDate orderDate) {
        this.products = new ArrayList<>();
        this.clientId = clientId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderDate = orderDate;
    }

    public Order(int clientId, double totalPrice, String address, LocalDate orderDate) {
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderDate = orderDate;
    }

    public Order(int orderId, int clientId, double totalPrice, String address, LocalDate orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        HashMap<Product,Integer> orderProducts = new HashMap<Product, Integer>();
        Connection connection = new DatabaseConnection().Connection();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orderhasproducts where orderid = " + orderId);
            while (resultSet.next()){
                Product product = databaseConnection.getProductById(resultSet.getInt("productid"));
                int quantity = resultSet.getInt("quantity");
                orderProducts.put(product,quantity);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        final String[] show = {"Order id: " + orderId + '\n' +
                /*"Client id: " + clientId + '\n' +*/
                "Products:\n"};
//        for(Product p : products)
//            show += p.toString() + '\n';

        orderProducts.forEach((key,value) -> show[0] += key  + "Number of products: " + value + '\n' + '\n');
        show[0] += "TotalPrice:" + totalPrice + '\n' +
                "Address: " + address + '\n' +
                "Order date: " + orderDate + '\n' + '\n';
        return show[0];
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
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
