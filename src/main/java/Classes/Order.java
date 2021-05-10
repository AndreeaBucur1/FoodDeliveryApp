package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private int clientId;
    private ArrayList<Product> products;
    private String adress;
    private LocalDate orderDate;
}
